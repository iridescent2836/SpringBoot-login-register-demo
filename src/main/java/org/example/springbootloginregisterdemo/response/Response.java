package org.example.springbootloginregisterdemo.response;

public class Response {
    private String code;
    private String message;
    private Object data; // Optional: Used to return additional data

    public Response(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public Response(String message, String code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    // Static factory methods for creating responses
    public static Response ok(String message) {
        return new Response(message, "200", null);
    }

    public static Response ok(String message, Object data) {
        return new Response(message, "200", data);
    }

    public static Response err(String message) {
        return new Response(message, "500", null);
    }

    public static Response err(String message, String code) {
        return new Response(message, code, null);
    }

    // Getters and Setters (if needed)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
