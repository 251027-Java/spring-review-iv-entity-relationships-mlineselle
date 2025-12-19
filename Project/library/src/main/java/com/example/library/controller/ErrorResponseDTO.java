package com.example.library.controller;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, int status, LocalDateTime timestamp) {
    
}