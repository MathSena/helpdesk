package com.mathsena.helpdesk.repository;

import com.mathsena.helpdesk.domain.Cliente;
import com.mathsena.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
