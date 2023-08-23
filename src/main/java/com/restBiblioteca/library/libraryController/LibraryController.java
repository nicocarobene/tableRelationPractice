package com.restBiblioteca.library.libraryController;

import com.restBiblioteca.library.Entity.Library;
import com.restBiblioteca.library.Repository.LibraryRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired
    LibraryRespository libraryEM;

    @PostMapping
    public ResponseEntity<Library> createLibrary(
            @Valid @RequestBody Library library
    ){
        Library  newLibrary= libraryEM.save(library);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLibrary);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Library> updateLibrary(
            @PathVariable long id,
            @Valid @RequestBody Library library
    ){
        Optional<Library> newLibrary= libraryEM.findById(id);
        if(newLibrary.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        library.setId(newLibrary.get().getId());
        return ResponseEntity.status(HttpStatus.FOUND).body(libraryEM.save(library));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Library> deleteLibrary(
            @PathVariable long id
    ){
        Optional<Library> newLibrary= libraryEM.findById(id);
        if(newLibrary.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping
    public ResponseEntity<Page<Library>> getAllLibrary(Pageable pageable){
        return ResponseEntity.ok(libraryEM.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibrary(
            @PathVariable long id
    ){
        Optional<Library> newLibrary= libraryEM.findById(id);
        return newLibrary.map(library -> ResponseEntity.status(HttpStatus.OK).body(library)).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }
}
