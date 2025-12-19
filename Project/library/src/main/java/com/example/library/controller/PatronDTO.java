package com.example.library.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.library.model.Loan;

public record PatronDTO(List<Loan> loans, Long id, String name, String email, LocalDate memberSince) {
    
}
