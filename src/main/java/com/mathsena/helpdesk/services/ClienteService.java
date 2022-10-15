package com.mathsena.helpdesk.services;


import com.mathsena.helpdesk.domain.Pessoa;
import com.mathsena.helpdesk.domain.Cliente;
import com.mathsena.helpdesk.domain.dtos.ClienteDTO;
import com.mathsena.helpdesk.repository.PessoaRepository;
import com.mathsena.helpdesk.repository.ClienteRepository;
import com.mathsena.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.mathsena.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id));

    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDto) {
        objDto.setId(null);
        validaPorCpfEEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return repository.save(newObj);
    }

    private void validaPorCpfEEmail(ClienteDTO objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());

        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDto.getId())) {
            throw new DataIntegrityViolationException("CPF Já cadastrado no sistema");

        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDto.getId())) {
            throw new DataIntegrityViolationException("E-mail Já cadastrado no sistema");

        }

    }

    public Cliente update(Integer id, @Valid ClienteDTO objDto) {
        objDto.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(objDto);
        oldObj = new Cliente(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);

        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possuí ordens de serviço e não pode ser deletado!");
        } else {
            repository.deleteById(id);
        }
    }
}
