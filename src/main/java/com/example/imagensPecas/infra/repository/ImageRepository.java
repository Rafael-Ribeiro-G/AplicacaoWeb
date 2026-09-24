package com.example.imagensPecas.infra.repository;

import com.example.imagensPecas.domain.entity.Image;
import com.example.imagensPecas.domain.enums.ImageExtension;
import com.example.imagensPecas.infra.repository.specs.GenericSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.imagensPecas.infra.repository.specs.ImageSpecs.*;
import static org.springframework.data.jpa.domain.Specification.where;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {
    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){

        Specification<Image> spec = where(GenericSpecs.conjunction());


        //SELECT * FROM IMAGE WHERE 1 = 1
        //Specification<Image> conjunction = (root, query1, criteriaBuilder) -> criteriaBuilder.conjunction();
        //Specification<Image> spec = Specification.where(conjunction);


        if(extension != null){
            //AND EXTENSION = 'PNG'
            //cb.equal... ---> monta a condição de igualdade //root.get... ---> acessa o campo extension da entidade Image
            //Specification<Image> extensionEqual = (root, q, cb) -> cb.equal(root.get("extension"), extension);
            //Concatena uma nova condição com as características específicas já existentes
            //Reatribui o resultado de volta a veriável spec

            spec =spec.and(extensionEqual(extension));
        }
        //Encapsula as três verificações de uma só vez, irá retornar string vazia FALSE ("" ou " ") quando a afirmação for falsa, apenas voltará como TRUE
        if(StringUtils.hasText(query)){
            // AND (NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY)
            // cb.upper ---> converte o vaor vindo do banco para caixa alta
            // query.toUpperCase() ---> converte o texto também para caixa alta e torna o termo de comparação em case-insesitive, no caso vai ignorar se o texto estiver em maiúscula ou minúscula
            // "%" representa qualquer sequência de caracteres
            //Specification<Image> nameLike = (root, q, cb) -> cb.like(cb.upper(root.get("name")), "%" + query.toUpperCase() +"%");
            //Specification<Image> tagsLike = (root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + query.toUpperCase() +"%");

            // Specification.anyOf() ---> metodo que combina múltiplas especificações usando OR - é necessário que apenas uma delas seja verdadeira
            //Specification<Image> nameOrTagsLike = Specification.anyOf(nameLike, tagsLike);

            spec = spec.and(Specification.anyOf(nameLike(query), tagsLike(query)));
        }
        return findAll(spec);
    }

}
