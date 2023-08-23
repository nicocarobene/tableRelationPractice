package com.restBiblioteca.person.Repository;

import com.restBiblioteca.person.Entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRespository extends JpaRepository<Party, Long> {
}
