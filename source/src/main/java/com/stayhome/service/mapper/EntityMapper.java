package com.stayhome.service.mapper;

import com.stayhome.util.CollectionHelper;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper <D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    default List <E> toEntity(List<D> dtos) {
        return CollectionHelper.map(dtos, this::toEntity);
    }

    default List <D> toDto(List<E> entities) {
        return CollectionHelper.map(entities, this::toDto);
    }
}
