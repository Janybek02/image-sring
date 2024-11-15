package com.example.image.entity;


public class ImageUploadResponse {
    private String message;

    // Constructor
    public ImageUploadResponse(String message) {
        this.message = message;
    }

    // Getter for the message
    public String getMessage() {
        return message;
    }

    // Setter for the message
    public void setMessage(String message) {
        this.message = message;
    }
}

