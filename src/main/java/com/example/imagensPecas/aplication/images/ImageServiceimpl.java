package com.example.imagensPecas.aplication.images;

import com.example.imagensPecas.domain.entity.Image;
import com.example.imagensPecas.domain.service.ImageService;
import com.example.imagensPecas.infra.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceimpl implements ImageService {

    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public Optional<Image> getById(String id) {
        return repository.findById(id);
    }
}
