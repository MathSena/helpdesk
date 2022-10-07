package com.mathsena.helpdesk.repository;

import com.mathsena.helpdesk.domain.Chamado;
import com.mathsena.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
