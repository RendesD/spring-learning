package com.david.mainservice.controller;

import com.david.mainservice.model.Book;
import com.david.mainservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/books")
@AllArgsConstructor
@Tag(name = "Book endpoints")
public class BookController {

    private final BookService bookService;

    @Operation(
            description = "Get all books",
            summary = "Get a list of all books"
    )
    @ApiResponse(
            responseCode = "200",
            description = "The request succeeded.",
            content = @Content(schema = @Schema(implementation = List.class))
    )
    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @Operation(
            description = "Find books by title",
            summary = "Get a list of books with a specific title"
    )
    @ApiResponse(
            responseCode = "200",
            description = "The request succeeded.",
            content = @Content(schema = @Schema(implementation = List.class))
    )
    @GetMapping("/title/{bookTitle}")
    public ResponseEntity<List<Book>> findByTitle(@PathVariable String bookTitle) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findByTitle(bookTitle));
    }

    @Operation(
            description = "Find a book by ID",
            summary = "Get a book by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The request succeeded.",
                    content = @Content(schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found."
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> findOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findOne(id));
    }

    @Operation(
            description = "Create a new book",
            summary = "Create a new book entry"
    )
    @ApiResponse(
            responseCode = "201",
            description = "The book was created successfully.",
            content = @Content(schema = @Schema(implementation = Book.class))
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @Operation(
            description = "Delete a book by ID",
            summary = "Delete a book by its ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "The book was deleted successfully."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Update a book by ID",
            summary = "Update a book's information by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The request succeeded.",
                    content = @Content(schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found."
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(book, id));
    }
}
