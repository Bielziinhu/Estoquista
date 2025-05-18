package br.com.ifba.infrastructure.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private int statusCode;
    private String message;
    private String stackTrace;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
