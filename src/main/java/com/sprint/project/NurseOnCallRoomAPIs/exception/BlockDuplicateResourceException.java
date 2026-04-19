package com.sprint.project.NurseOnCallRoomAPIs.exception;

public class BlockDuplicateResourceException extends RuntimeException {

    public BlockDuplicateResourceException(String message) {
        super(message);
    }

    public BlockDuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
