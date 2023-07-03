package com.example.backend.models.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
public class ReferenceMapper {
    @PersistenceContext
    private EntityManager entityManager;

    @ObjectFactory
    public <T> T map(final Integer id, @TargetType Class<T> type) {
        return entityManager.getReference(type, id);
    }
}
