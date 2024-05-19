package com.eneskaya.veterinarymanagementsystem.core.result;

import lombok.Getter;

@Getter
public class Result {

    //Custom response Style

    private boolean status;
    private String message;
    private String code;

    public Result(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
