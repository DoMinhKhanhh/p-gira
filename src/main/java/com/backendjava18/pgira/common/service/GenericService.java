package com.backendjava18.pgira.common.service;

import com.backendjava18.pgira.common.model.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface GenericService<T extends BaseEntity, DTO, ID> {

    JpaRepository<T, ID> getRepository(); //Factory method - creation design pattern

    ModelMapper getMapper();

    default List<T> findAll() {
        return getRepository().findAll();
    }

    default List<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable)
                .stream().collect(Collectors.toList());
    }

    default List<DTO> findAllDto(Pageable pageable,Class<DTO> dtoClass){
        return getRepository().findAll(pageable)
                .stream()
                .map(model -> getMapper().map(model,dtoClass))
                .collect(Collectors.toList());
    }

    default Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    default T save(T entity) {
        return getRepository().save(entity);
    }

    default void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    default T update(T entity) {
        return getRepository().save(entity);
    }

    default List<T> findByIds(List<ID> ids) {
        return getRepository().findAllById(ids);
    }

    default DTO save(DTO dto, Class<T> modelClass, Class<DTO> dtoClass) {
        //map object Dto thanh object model de save
        T model = getMapper().map(dto, modelClass);
        T savedModel = getRepository().save(model);
        // map du lieu sang dto va tra ve
        return getMapper().map(savedModel, dtoClass);

    }
}
