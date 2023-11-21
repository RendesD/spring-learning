package com.david.mainservice.service;

import com.david.mainservice.exceptions.BookIdMismatchException;
import com.david.mainservice.exceptions.BookNotFoundException;
import com.david.mainservice.model.Book;
import com.david.mainservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    public Book findOne(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " not found."));
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " not found."));
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book, Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException("Id provided does not match with Book provided");
        }
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " not found."));
        return bookRepository.save(book);
    }
}
