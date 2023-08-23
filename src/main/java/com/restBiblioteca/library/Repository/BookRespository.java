package com.restBiblioteca.library.Repository;

import com.restBiblioteca.library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRespository extends JpaRepository<Book, Long> {
}
