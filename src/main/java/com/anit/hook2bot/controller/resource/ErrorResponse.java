package com.anit.hook2bot.controller.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;
}