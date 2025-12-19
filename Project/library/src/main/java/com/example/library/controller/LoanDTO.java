package com.example.library.controller;

import java.time.LocalDate;

public record LoanDTO(Long id, Long bookId, Long patronId, LocalDate loanDate, LocalDate dueDate) {
    
}
