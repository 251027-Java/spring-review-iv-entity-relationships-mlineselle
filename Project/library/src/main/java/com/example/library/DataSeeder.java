package com.example.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.library.model.Book;
import com.example.library.model.Patron;
import com.example.library.repository.BookRepository;
import com.example.library.repository.PatronRepository;

@Component
public class DataSeeder implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public DataSeeder(BookRepository bookRepository, PatronRepository patronRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            seedBooks();
        }
        if (patronRepository.count() == 0) {
            seedPatrons();
        }
    }

    private void seedBooks() {
        bookRepository.save(new Book("Clean Code", "Robert Martin", null));
        bookRepository.save(new Book("The Pragmatic Programmer", "David Thomas", null));
        bookRepository.save(new Book("Design Patterns", "Gang of Four", null));
        bookRepository.save(new Book("Effective Java", "Joshua Bloch", null));
        bookRepository.save(new Book("Spring in Action", "Craig Walls", null));
    }
    
    private void seedPatrons() {
        patronRepository.save(new Patron("Richard Hawkins", "richard@gmail.com"));
        patronRepository.save(new Patron("Patron 2", "patrons@gmail.com"));
    }
}
