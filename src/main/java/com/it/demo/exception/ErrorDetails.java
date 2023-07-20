package com.it.demo.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorDetails(LocalDateTime timestamp, String message, String path, String errorCode) {}
