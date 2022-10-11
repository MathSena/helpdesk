package com.mathsena.helpdesk.services;


import com.mathsena.helpdesk.domain.Tecnico;
import com.mathsena.helpdesk.repository.TecnicoRepository;
import com.mathsena.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id));

    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }
}
