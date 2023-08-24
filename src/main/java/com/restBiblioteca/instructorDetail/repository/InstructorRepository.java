package com.restBiblioteca.instructorDetail.repository;

import com.restBiblioteca.instructorDetail.entity.Intructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Intructor, Long> {
}
