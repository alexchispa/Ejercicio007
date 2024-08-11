package es.cic.curso.ejercicio007.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.ejercicio007.model.Book;
import es.cic.curso.ejercicio007.service.BookService;

@RestController
@RequestMapping("/Books")
public class BookController {

    @Autowired
    private BookService BookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty() ||
            book.getAuthor() == null || book.getAuthor().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Book newBook = BookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @GetMapping()
    public ResponseEntity<Book> getBook(
    @RequestParam(required = true) String title,
    @RequestParam(required = true) String author,
    @RequestParam(required = false) int year) {
        Book bookToFind = new Book(title, author, year);
        Book book = BookService.getBook(bookToFind);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }
}