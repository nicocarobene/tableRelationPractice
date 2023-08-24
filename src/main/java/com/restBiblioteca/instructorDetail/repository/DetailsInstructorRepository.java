package com.restBiblioteca.instructorDetail.repository;

import com.restBiblioteca.instructorDetail.entity.DetailsInstructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsInstructorRepository extends JpaRepository<DetailsInstructor, Long> {
}
