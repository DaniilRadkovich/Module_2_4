package com.radkovich.module_2_4.exception;

public class EntityNotFoundException extends RepositoryException {
    private final String entityType;
    private final Integer entityId;

    public EntityNotFoundException(String entityType, Integer entityId) {
        super(entityType + " not found with id: " + entityId);
        this.entityType = entityType;
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public Integer getEntityId() {
        return entityId;
    }
}
