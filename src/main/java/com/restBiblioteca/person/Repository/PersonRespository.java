package com.restBiblioteca.person.Repository;

import com.restBiblioteca.person.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PersonRespository extends JpaRepository<Person, Long> {
}
