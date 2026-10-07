package com.example.imagensPecas.domain.enums;

import lombok.Getter;
import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ImageExtension {
    PNG (MediaType.IMAGE_PNG),
    JPG (MediaType.IMAGE_JPEG),
    GIF (MediaType.IMAGE_GIF);

    @Getter
    private final MediaType mediaType;

    ImageExtension(MediaType mediaType){
        this.mediaType = mediaType;
    }

    public static ImageExtension valueof(MediaType mediaType){
        return Arrays.stream(values())
                .filter(ie -> ie.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }

    //Vai converter uma String em ImageExtension sem lançar exceção
    public static ImageExtension ofName(String name){
        //Filtra os valores do Enum e filtra pelo que corresponde
        //se name for NULL, a comparação retorna FALSE, no caso o filter não encontra nenhum correspondência para nenhum valor
        return Arrays.stream(values())
                //a comparação funciona independente de as letras serem maiúsculas ou minúsculas
                .filter(ie -> ie.name().equalsIgnoreCase(name))
                .findFirst()
                //se a correspondência for NULL, o metodo vai retornar uma resposta NULL
                .orElse(null);
    }
}