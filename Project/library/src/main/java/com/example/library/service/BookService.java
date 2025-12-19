package com.example.library.service;

import com.example.library.controller.BookDTO;
import com.example.library.controller.BookWOIDDTO;
import com.example.library.exception.BookNotAvailableException;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * BookService
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::BookToDTO).toList();
    }

    public BookDTO findById(Long id) {
        Optional<Book> res = bookRepository.findById(id);
        return (res.isEmpty() ? null : BookToDTO(res.get()));
    }

    public BookDTO addBook(BookWOIDDTO book) {
        Book newBook = new Book(book.title(), book.author(), book.isbn());
        return BookToDTO(bookRepository.save(newBook));
    }

    public BookDTO checkoutBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));

        if (!book.getAvailable()) {
            throw new BookNotAvailableException(
                    "Book with id " + bookId + " is already checked out");
        }

        book.setAvailable(false);
        return BookToDTO(bookRepository.save(book));
    }

    public BookDTO returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));

        if (book.getAvailable()) {
            throw new BookNotAvailableException(
                    "Book with id " + bookId + " is already available");
        }

        book.setAvailable(true);
        return BookToDTO(bookRepository.save(book));
    }

    private BookDTO BookToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getAvailable());
    }
}
