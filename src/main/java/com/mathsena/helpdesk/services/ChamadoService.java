package com.mathsena.helpdesk.services;

import com.mathsena.helpdesk.domain.Chamado;
import com.mathsena.helpdesk.domain.Cliente;
import com.mathsena.helpdesk.domain.Tecnico;
import com.mathsena.helpdesk.domain.dtos.ChamadoDTO;
import com.mathsena.helpdesk.domain.enums.Prioridade;
import com.mathsena.helpdesk.domain.enums.Status;
import com.mathsena.helpdesk.repository.ChamadoRepository;
import com.mathsena.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(@Valid ChamadoDTO objDto) throws IllegalAccessException {
        return repository.save(newChamado(objDto));

    }

    private Chamado newChamado(ChamadoDTO obj) throws IllegalAccessException {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());
        Chamado chamado = new Chamado();

        if (obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        if (obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());

        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;

    }


    public Chamado update(Integer id, ChamadoDTO objDto) throws IllegalAccessException {
        objDto.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDto);
        return repository.save(oldObj);
    }
}

