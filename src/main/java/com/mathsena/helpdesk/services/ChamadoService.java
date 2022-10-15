package com.mathsena.helpdesk.services;

import com.mathsena.helpdesk.domain.Chamado;
import com.mathsena.helpdesk.repository.ChamadoRepository;
import com.mathsena.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado: " + id));
    }
}
