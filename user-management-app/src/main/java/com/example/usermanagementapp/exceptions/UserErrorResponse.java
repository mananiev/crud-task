package com.example.usermanagementapp.exceptions;

public class UserErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public UserErrorResponse() {
    }

    public UserErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public UserErrorResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public UserErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public UserErrorResponse setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
