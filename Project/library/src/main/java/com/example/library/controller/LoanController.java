package com.example.library.controller;

import com.example.library.service.LoanService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController
 */
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/active")
    public List<LoanDTO> getActiveLoans() {
        return loanService.getActiveLoans();
    }

    @GetMapping("/patrons/{id}/loans")
    public List<LoanDTO> getPatronLoans(@PathVariable Long id) {
        return loanService.getLoansByPatron(id);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<LoanDTO> returnLoan(@PathVariable Long id) {
        LoanDTO loan = loanService.returnLoan(id);
        return ResponseEntity.ok(loan);
    }

    @PostMapping
    public ResponseEntity<LoanDTO> addLoan(@Valid @RequestBody NewLoanDTO loan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(loan.bookId(), loan.patronId()));
    }

}
