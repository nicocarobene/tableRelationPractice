package com.restBiblioteca.library.libraryController;

import com.restBiblioteca.library.Entity.Book;
import com.restBiblioteca.library.Entity.Library;
import com.restBiblioteca.library.Repository.BookRespository;
import com.restBiblioteca.library.Repository.LibraryRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookRespository bookEM;
    @Autowired
    private LibraryRespository libraryEM;
    @PostMapping
    public ResponseEntity<Book> createBook(
            @Valid @RequestBody Book book
    ){
        Optional<Library> library= libraryEM.findById(book.getLibrary().getId());
        if(library.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        book.setLibrary(library.get());
        Book  newBook= bookEM.save(book);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBook.getId()).toUri();
        return ResponseEntity.created(uri).body(newBook);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable long id,
        @Valid @RequestBody Book book
    ){
        Optional<Library> library= libraryEM.findById(book.getLibrary().getId());
        Optional<Book> newBook= bookEM.findById(id);
        if(library.isEmpty() | newBook.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        book.setId(newBook.get().getId());
        book.setLibrary(library.get());
        return ResponseEntity.status(HttpStatus.FOUND).body(bookEM.save(book));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Library> deleteBook(
            @PathVariable long id
    ){
        Optional<Book> newBook= bookEM.findById(id);
        if(newBook.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping
    public ResponseEntity<Page<Book>> getAllLibrary(Pageable pageable){
        return ResponseEntity.ok(bookEM.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getLibrary(
            @PathVariable long id
    ){
        Optional<Book> newBook= bookEM.findById(id);
        return newBook.map(book -> ResponseEntity.status(HttpStatus.OK).body(book)).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }
}

