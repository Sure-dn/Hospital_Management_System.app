package com.sprint.project.nurseoncallroom.dto;

public class ResponseStructure<T> {

    private int statusCode;
    private String message;
    private T data;

    public ResponseStructure() {}

    public ResponseStructure(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // ✅ SUCCESS
    public static <T> ResponseStructure<T> success(int statusCode, String message, T data) {
        return new ResponseStructure<>(statusCode, message, data);
    }

    // 🔥 ADD THIS (IMPORTANT)
    public static <T> ResponseStructure<T> error(int statusCode, String message) {
        return new ResponseStructure<>(statusCode, message, null);
    }

    // getters & setters
    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}