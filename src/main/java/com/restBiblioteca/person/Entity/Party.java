package com.restBiblioteca.person.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "party",
        uniqueConstraints = @UniqueConstraint(columnNames = {"party_id"}))
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private long id;

    private String localization;
    @Column(name = "date_party")
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "persons_party",
            joinColumns = @JoinColumn(name = "party_id",referencedColumnName = "party_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"party_id", "person_id"})
    )
    private Set<Person> people = new HashSet<>();

    public Party(){}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }
}
