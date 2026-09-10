package com.example.imagensPecas.domain.service;

import com.example.imagensPecas.domain.entity.Image;

import java.util.Optional;

public interface ImageService {
    Image save (Image image);

    Optional<Image> getById(String id);
}
