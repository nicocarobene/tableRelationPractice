package com.restBiblioteca.person.controller;

import com.restBiblioteca.person.Entity.Party;
import com.restBiblioteca.person.Entity.Person;
import com.restBiblioteca.person.Repository.PartyRespository;
import com.restBiblioteca.person.Repository.PersonRespository;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
@RestController
@RequestMapping("/api/party")
public class PartyController {
    @Autowired
    private PartyRespository partyRespository;

    @GetMapping
    public ResponseEntity<Collection<Party>> getAllParty(){
        return ResponseEntity.status(HttpStatus.OK).body(partyRespository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Party> getPartyById(
            @PathVariable Long id
    ){
        Optional<Party> findParty= partyRespository.findById(id);
        return findParty.map(party -> ResponseEntity.status(HttpStatus.OK).body(party)).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }
    @GetMapping("/{id}/person")
    public ResponseEntity<Collection<Person>> getPersonParty(
            @PathVariable Long id
    ){
        Optional<Party> findParty= partyRespository.findById(id);
        return findParty.<ResponseEntity<Collection<Person>>>map(person -> ResponseEntity.ok(person.getPeople())).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @PostMapping
    public ResponseEntity<Party> createParty(
            @Valid @RequestBody Party party
    ){
        return ResponseEntity.ok(partyRespository.save(party));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParty (
            @PathVariable Long id
    ){
        Optional<Party> findParty= partyRespository.findById(id);
        if(findParty.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        partyRespository.deleteById(findParty.get().getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
