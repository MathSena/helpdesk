package com.mathsena.helpdesk.services;


import com.mathsena.helpdesk.domain.Pessoa;
import com.mathsena.helpdesk.domain.Tecnico;
import com.mathsena.helpdesk.domain.dtos.TecnicoDTO;
import com.mathsena.helpdesk.repository.PessoaRepository;
import com.mathsena.helpdesk.repository.TecnicoRepository;
import com.mathsena.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.mathsena.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id));

    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDto) {
        objDto.setId(null);
        validaPorCpfEEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());

        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDto.getId())) {
            throw new DataIntegrityViolationException("CPF Já cadastrado no sistema");

        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDto.getId())) {
            throw new DataIntegrityViolationException("E-mail Já cadastrado no sistema");

        }

    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDto);
        oldObj = new Tecnico(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Tecnico possuí ordens de serviço e não pode ser deletado!");
        } else {
            repository.deleteById(id);
        }
    }
}
