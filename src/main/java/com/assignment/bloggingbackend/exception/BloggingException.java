package com.assignment.bloggingbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloggingException extends RuntimeException {

    private String code;
    private String description;

    public BloggingException(String message) {
        super(message);
    }
}
