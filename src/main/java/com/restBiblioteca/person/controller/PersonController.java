package com.restBiblioteca.person.controller;

import com.restBiblioteca.person.Entity.Party;
import com.restBiblioteca.person.Entity.Person;
import com.restBiblioteca.person.Repository.PersonRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/person")
public class PersonController {
    @Autowired
    private PersonRespository personRespository;

    @GetMapping
    public ResponseEntity<Collection<Person>> getAllPerson(){
        return ResponseEntity.status(HttpStatus.OK).body(personRespository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(
            @PathVariable Long id
    ){
        Optional<Person> findPerson= personRespository.findById(id);
        return findPerson.map(person -> ResponseEntity.status(HttpStatus.OK).body(person)).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }
    @GetMapping("/{id}/party")
    public ResponseEntity<Collection<Party>> getPartyPerson(
            @PathVariable Long id
        ){
        Optional<Person> findPerson= personRespository.findById(id);
        return findPerson.<ResponseEntity<Collection<Party>>>map(person -> ResponseEntity.ok(person.getParties())).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(
            @Valid @RequestBody Person person
    ){
        return ResponseEntity.ok(personRespository.save(person));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson (
            @PathVariable Long id
    ){
        Optional<Person> findPerson= personRespository.findById(id);
        if(findPerson.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        personRespository.deleteById(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
