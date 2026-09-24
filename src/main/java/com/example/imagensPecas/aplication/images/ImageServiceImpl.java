package com.example.imagensPecas.aplication.images;

import com.example.imagensPecas.domain.entity.Image;
import com.example.imagensPecas.domain.enums.ImageExtension;
import com.example.imagensPecas.domain.service.ImageService;
import com.example.imagensPecas.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

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

    @Override
    public List<Image> search(ImageExtension extension, String query) {
        return repository.findByExtensionAndNameOrTagsLike(extension, query);
    }
}
