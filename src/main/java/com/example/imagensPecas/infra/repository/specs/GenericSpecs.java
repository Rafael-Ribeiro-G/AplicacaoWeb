package com.example.imagensPecas.infra.repository.specs;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecs {
    private GenericSpecs(){}

    //Parâmetro do tipo genérico
    public static <T> Specification<T> conjunction(){
        return(root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}
