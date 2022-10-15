package com.kornello.controller;

import com.kornello.service.NasaPictureService;
import com.kornello.model.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NasaPictureController {
    private final NasaPictureService service;

    final HttpHeaders headers = new HttpHeaders() {{
        add("content-type","image/jpeg");
    }};

    @GetMapping({"/mars/pictures/largest/{sol}", "/mars/pictures/largest/{sol}/{camera}"})
    public ResponseEntity<byte[]> getLargestPicture(@PathVariable String sol, @PathVariable(required = false) String cam) {
        return new ResponseEntity<>(service.getLargestPic(sol, cam).getImg(), headers, OK);
    }
}
