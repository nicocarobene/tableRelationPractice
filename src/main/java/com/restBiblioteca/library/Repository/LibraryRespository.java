package com.restBiblioteca.library.Repository;

import com.restBiblioteca.library.Entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRespository extends JpaRepository<Library, Long> {
}
