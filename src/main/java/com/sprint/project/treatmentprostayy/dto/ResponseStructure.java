package com.sprint.project.treatmentprostayy.dto;

import java.time.LocalDateTime;

public class ResponseStructure<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ResponseStructure() {}

    public ResponseStructure(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Getters & Setters

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public LocalDateTime getTimestamp() { return timestamp; }
}
