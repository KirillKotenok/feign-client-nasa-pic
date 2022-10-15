package com.kornello.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Picture {
    private String imgSrc;
    private Long size;
    private byte[] img;

    public Picture(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
