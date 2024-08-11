package es.cic.curso.ejercicio007.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.cic.curso.ejercicio007.model.Book;

@Service
public class BookService {

    private final List<Book> books = new LinkedList<>();
    
    public Book addBook(Book book) {
        boolean bookAdded = books.add(book);
        if(bookAdded){
            return book;
        }
        
        return null;
    }

    public Book getBook(Book book) {
        int bookIndex = books.indexOf(book);
        
        if (bookIndex == -1) {
            return null;
        }
        return books.get(bookIndex);
    }

}

