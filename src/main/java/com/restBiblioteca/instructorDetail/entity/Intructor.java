package com.restBiblioteca.instructorDetail.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "instructor")
public class Intructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String lastname;
    @Email
    private String email;

    @OneToOne
    @JoinColumn(name = "details_instructor")
    private DetailsInstructor detailsInstructor;

    public DetailsInstructor getDetailsInstructor() {
        return detailsInstructor;
    }

    public void setDetailsInstructor(DetailsInstructor detailsInstructor) {
        this.detailsInstructor = detailsInstructor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
