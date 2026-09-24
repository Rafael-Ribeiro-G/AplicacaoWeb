package com.example.imagensPecas.aplication.images;

import com.example.imagensPecas.domain.entity.Image;
import com.example.imagensPecas.domain.enums.ImageExtension;
import com.example.imagensPecas.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService service;
    private final ImageMapper mapper;

    @PostMapping
    public ResponseEntity save(
            @RequestParam("file")MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) throws IOException
    {
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());

        Image image = mapper.mapToImage(file, name, tags);
        Image savedImage = service.save(image);

        URI imageUri = buildImageURI(savedImage);
        return ResponseEntity.created(imageUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id){
        var possibleImage = service.getById(id);
        if(possibleImage.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var image = possibleImage.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtension().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("inline; filename = \"" + image.getName() + "\"",image.getFileName());
        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    //localhost:8080/v1/images/id_da_imagem
    private URI buildImageURI(Image image){
        String imagePath = "/" + image.getId();
        //Retorna apenas a URI
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path(imagePath).build().toUri();
    }

    //localhost:8080/v1/images?extension=PNG&quey=Nature
    //@GetMapping sem nenhum parâmetro mapeia para a raiz do controller, os parâmetros não vão fazer parte da URL em si
    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(
            //Informa que esse parâmetro deve ser retirado da query string URL
            @RequestParam(value = "extension", required = false, defaultValue = "") String extension,
            @RequestParam(value = "query", required = false) String query){

        //Valores ausentes ou vazio resultam em NULL
        var result = service.search(ImageExtension.ofName(extension), query);
        var images = result.stream().map(image -> {
            var url = buildImageURI(image);
            return mapper.imageDTO(image,url.toString());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(images);
    }
}