package com.example.imagensPecas;

import com.example.imagensPecas.domain.entity.Image;
import com.example.imagensPecas.domain.enums.ImageExtension;
import com.example.imagensPecas.infra.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImagensPecasApplication{

    /*
    @Bean
    public CommandLineRunner commandLineRunner (@Autowired ImageRepository repository) {
        return args -> {
            Image image = Image.builder()
                    .extension(ImageExtension.PNG)
                    .name("myimage")
                    .tags("teste")
                    .size(1000L)
                    .build();
            repository.save(image);
        };

    }
    */

    public static void main (String[] args) {
        SpringApplication.run(ImagensPecasApplication.class, args);
    }
}
