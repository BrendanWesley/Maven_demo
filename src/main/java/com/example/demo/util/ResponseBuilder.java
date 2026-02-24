package com.example.demo.util;

import com.example.demo.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(ApiResponse.success(message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        return ResponseEntity.ok(ApiResponse.success(message));
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> accepted(String message, T data) {
        return ResponseEntity.accepted().body(ApiResponse.success(message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return ResponseEntity.badRequest().body(ApiResponse.error(message, "BAD_REQUEST"));
    }

    public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(message, "UNAUTHORIZED"));
    }

    public static <T> ResponseEntity<ApiResponse<T>> forbidden(String message) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.error(message, "FORBIDDEN"));
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(message, "NOT_FOUND"));
    }

    public static <T> ResponseEntity<ApiResponse<T>> conflict(String message) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(message, "CONFLICT"));
    }

    public static <T> ResponseEntity<ApiResponse<T>> internalServerError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(message, "INTERNAL_SERVER_ERROR"));
    }
}
