package com.example.imagensPecas.domain.enums;

import org.springframework.http.MediaType;

public enum ImageExtension {
    PNG,
    GIF,
    JPEG;

    // Esse é o método que o seu professor criou no Enum para aceitar MediaType!
    public static ImageExtension valueOf(MediaType mediaType) {
        if (mediaType == null) {
            return null;
        }
        String subtype = mediaType.getSubtype().toUpperCase();
        return ImageExtension.valueOf(subtype);
    }
}