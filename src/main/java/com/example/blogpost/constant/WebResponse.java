package com.example.blogpost.constant;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class WebResponse<T> {

    private final int code;
    private final String message;
    private final T data;

    public WebResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public WebResponse() {
        this(HttpStatus.OK.value(), HttpStatus.OK.name(), null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
