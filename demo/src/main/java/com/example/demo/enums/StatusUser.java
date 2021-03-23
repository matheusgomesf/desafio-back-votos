package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum StatusUser {
    UNABLE_TO_VOTE("UNABLE_TO_VOTE"),
    ABLE_TO_VOTE("ABLE_TO_VOTE");

    private final String status;

    StatusUser(String status) {
        this.status = status;
    }
}
