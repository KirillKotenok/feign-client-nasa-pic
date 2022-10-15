package com.kornello.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nasaPictures", url = "https://api.nasa.gov/")
public interface FeignPictureService {
    @GetMapping("/mars-photos/api/v1/rovers/curiosity/photos?api_key={apiKey}&sol={sol}&camera={cam}")
    public JsonNode getPicturesBySolAndCamera(@RequestParam String apiKey, @PathVariable(required = false) String sol,
                                              @PathVariable(required = false) String cam);
}