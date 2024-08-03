package Lap2.example.Lap2;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class MapDto {
    public static <T, D> D toModel(T entity, Class<D> dtoClass) {
        if (entity == null) {
            return null;
        }

        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Error creating DTO instance", e);
        }
    }

    public static <T, D> List<D> toModels(List<T> entities, Class<D> dtoClass) {
        return entities.stream()
                .map(entity -> toModel(entity, dtoClass))
                .collect(Collectors.toList());
    }

}
