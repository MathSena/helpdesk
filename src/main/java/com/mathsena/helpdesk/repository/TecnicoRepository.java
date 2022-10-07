package com.mathsena.helpdesk.repository;

import com.mathsena.helpdesk.domain.Pessoa;
import com.mathsena.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
