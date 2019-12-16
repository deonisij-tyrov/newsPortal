package com.denis.newsportal.newsportal.converter;

import java.util.ArrayList;
import java.util.List;

public interface DtoEntityConverter<T, B> {

    T convertToDto(final B dbo);

    B convertToDbo(final T dto);

    default List<T> convertToDto(final List<B> entitySet) {
        if (entitySet != null) {
            final List<T> dtoSet = new ArrayList<>();
            for (final B entity : entitySet) {
                final T dto = convertToDto(entity);
                dtoSet.add(dto);
            }
            return dtoSet;
        }
        return null;
    }

    default List<B> convertToDbo(final List<T> dtoSet) {
        if (dtoSet != null) {
            final List<B> entitySet = new ArrayList<>();
            for (final T dto : dtoSet) {
                final B entity = convertToDbo(dto);
                entitySet.add(entity);
            }
            return entitySet;
        }
        return null;
    }


}
