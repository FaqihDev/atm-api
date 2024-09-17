package com.jamsirat.atmapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class HttpResponse<T> {

    private String message;
    private String developerMessage;
    private String timeStamp;
    private HttpStatus status;
    private int statusCode;
    private T data;


    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {
        private long totalElements;
        private int totalPages;
        private int page;
        private int size;
    }


    public static <T> HttpResponse<T> build(String developerMessage, String message, HttpStatus status, T data) {
        return HttpResponse.<T>builder()
                .timeStamp(LocalDateTime.now().toString())
                .developerMessage(developerMessage)
                .message(message)
                .status(status)
                .statusCode(status.value())
                .data(data)
                .build();
    }



    public static <T> HttpResponse<T> noContent() {
        return build("Data is empty", "No content", HttpStatus.NO_CONTENT, null);
    }

    public static <T> HttpResponse<T> unauthorized() {
        return build("Unauthorized", "Access Denied", HttpStatus.UNAUTHORIZED, null);
    }

    public static <T> HttpResponse<T> invalidatedToken() {
        return build("Token is Expired or Invalid", "Please do login!", HttpStatus.UNAUTHORIZED, null);
    }

    public static <T> HttpResponse<T> outOfStock() {
        return build("Sorry we are out of stock", "Please contact your admin!", HttpStatus.BAD_REQUEST, null);
    }
}
