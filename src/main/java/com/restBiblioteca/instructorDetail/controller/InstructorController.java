package com.restBiblioteca.instructorDetail.controller;

import com.restBiblioteca.comment.exeption.ResourceNotFoundExeption;
import com.restBiblioteca.instructorDetail.entity.Intructor;
import com.restBiblioteca.instructorDetail.repository.DetailsInstructorRepository;
import com.restBiblioteca.instructorDetail.repository.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    DetailsInstructorRepository detailsInstructorRepository;

    @GetMapping
    public Page<Intructor> getALlInstructor(Pageable page){
        return instructorRepository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Intructor> getInstructorById(
            @PathVariable Long id
    ){
        return instructorRepository.findById(id).map(ResponseEntity::ok).orElseThrow(()-> new ResourceNotFoundExeption("Instructor by id: "+id+" is not found"));
    }
    @PostMapping
    public ResponseEntity<Intructor> createInstructori(
            @RequestBody Intructor intructor,
            @RequestParam Long detailsId
    ){
        System.out.println(intructor.getDetailsInstructor());

        return detailsInstructorRepository.findById(detailsId)
                .map(detail->{
                    intructor.setDetailsInstructor(detail);
                    instructorRepository.save(intructor);
                    return ResponseEntity.ok(intructor);
                }).orElseThrow(()-> new ResourceNotFoundExeption("DetailsInstructor by id: "+detailsId+". is not found"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Intructor> updateInstructor(
            @PathVariable Long id,
            @Valid @RequestBody Intructor intructor
    ){
        return instructorRepository.findById(id)
                .map(oldIntruc->{
                    oldIntruc.setEmail(intructor.getEmail());
                    oldIntruc.setLastname(intructor.getLastname());
                    oldIntruc.setName(intructor.getName());
                    return ResponseEntity.ok(instructorRepository.save(oldIntruc));
                }).orElseThrow(()-> new ResourceNotFoundExeption("Instructor by id: "+id+". is not found"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructor(
            @PathVariable Long id
    ){
       return instructorRepository.findById(id)
                .map(instruc->{
                    instructorRepository.delete(instruc);
                    Map<String, Boolean> resp= new HashMap<>();
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp.put("Instructor delete", Boolean.TRUE));
                }).orElseThrow(()-> new ResourceNotFoundExeption("Instructor by id: "+id+". is not found"));
    }
}
