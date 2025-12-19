package com.example.library.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BookWOIDDTO(
        @NotBlank(message = "Title cannot be blank") String title,
        @NotBlank(message = "Author cannot be blank") String author,
        @Pattern(regexp = "^[0-9-]+$", message = "Invalid ISBN format") String isbn) {

}
