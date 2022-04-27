package com.pusl2020project.groupproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnknownExeception extends RuntimeException {

    public UnknownExeception(String message) {
        super(message);
    }
}