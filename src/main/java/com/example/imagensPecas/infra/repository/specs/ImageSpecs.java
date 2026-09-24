package com.example.imagensPecas.infra.repository.specs;

import com.example.imagensPecas.domain.entity.Image;
import com.example.imagensPecas.domain.enums.ImageExtension;
import org.springframework.data.jpa.domain.Specification;

public class ImageSpecs {
    //Essa classe vai conter apenas metodos estáticos
    private ImageSpecs(){

    }
    public static Specification<Image> extensionEqual(ImageExtension extension){
        //Retorno copiado do ImageRepository - EXTENSION
        return(root, q, cb) -> cb.equal(root.get("extension"), extension);
    }
    public static Specification<Image> nameLike(String name){
        return(root, q, cb) -> cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%");
    }
    public static Specification<Image> tagsLike(String tags){
        return(root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + tags.toUpperCase() + "%");
    }
}
