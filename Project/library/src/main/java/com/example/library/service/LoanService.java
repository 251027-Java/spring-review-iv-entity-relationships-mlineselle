package com.example.library.service;

import com.example.library.controller.LoanDTO;
import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.LoanNotFoundException;
import com.example.library.exception.PatronNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.Patron;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.PatronRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * BookService
 */
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository,
            PatronRepository patronRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public LoanDTO createLoan(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron not found with id " + patronId));
        return LoanToDTO(loanRepository.save(new Loan(book, patron)));
    }

    public LoanDTO returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id " + loanId));
        loan.setReturnDate(LocalDate.now());
        return LoanToDTO(loanRepository.save(loan));
    }

    public List<LoanDTO> getActiveLoans() {
        return loanRepository.findAll().stream()
                .filter(loan -> loan.getReturnDate() == null)
                .map(this::LoanToDTO)
                .toList();
    }

    public List<LoanDTO> getLoansByPatron(Long patronId) {
        return patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron not found with id " + patronId))
                .getLoans().stream()
                .map(this::LoanToDTO)
                .toList();
    }

    private LoanDTO LoanToDTO(Loan loan) {
        return new LoanDTO(loan.getId(), loan.getBook().getId(), loan.getPatron().getId(), loan.getLoanDate(), loan.getDueDate());
    }
}
