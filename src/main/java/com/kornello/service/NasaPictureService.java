package com.kornello.service;

import com.kornello.model.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NasaPictureService {

    private final FeignPictureService pictureService;
    private final RestTemplate template;
    private static final String API_KEY = "6tttT1IDPRn64RM1YuKi6roj6yYforNpZ4eejugS";

    @Cacheable("largestPicture")
    public Picture getLargestPic(String sol, String cam) {
        return pictureService.getPicturesBySolAndCamera(API_KEY, sol, cam)
                .findValuesAsText("img_src")
                .stream()
                .map(Picture::new)
                .peek(this::getImageData)
                .max(Comparator.comparing(Picture::getSize))
                .orElseThrow(() -> new NoSuchElementException("No pictures found"));
    }

    private void getImageData(Picture picture) {
        var redirect = template.headForHeaders(URI.create(picture.getImgSrc()))
                .getLocation();
        var size = Long.parseLong(template.headForHeaders(redirect).getFirst("Content-Length"));
        var img = template.getForObject(redirect, byte[].class);

        picture.setSize(size);
        picture.setImg(img);
    }
}
