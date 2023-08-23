package com.restBiblioteca.person.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person",
uniqueConstraints = @UniqueConstraint(columnNames = {"person_id"}))
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    private String name;
    private int age;

    @OneToMany(
            mappedBy = "person",
            orphanRemoval = true)
    private Set<Skill> skills = new HashSet<>();
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JsonBackReference
    @JoinTable(
            name = "persons_party",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "party_id",  referencedColumnName = "party_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "party_id"})
    )
    private Set<Party> parties= new HashSet<>();

    public Person(){}

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Party> getParties() {
        return parties;
    }

    public void setParties(Set<Party> parties) {
        this.parties = parties;
    }
}
