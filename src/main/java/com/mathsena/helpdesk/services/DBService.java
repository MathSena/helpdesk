package com.mathsena.helpdesk.services;

import com.mathsena.helpdesk.domain.Chamado;
import com.mathsena.helpdesk.domain.Cliente;
import com.mathsena.helpdesk.domain.Tecnico;
import com.mathsena.helpdesk.domain.enums.Perfil;
import com.mathsena.helpdesk.domain.enums.Prioridade;
import com.mathsena.helpdesk.domain.enums.Status;
import com.mathsena.helpdesk.repository.ChamadoRepository;
import com.mathsena.helpdesk.repository.ClienteRepository;
import com.mathsena.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;


    public void instanciaDB() {


        Tecnico tec1 = new Tecnico(null, "Matheus Sena", "32124117966", "MathSena07@hotmail.com", "41494899");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Roberta Poiato", "76898709076", "rpoiato@gmail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 01", "Restore de Arquivo", tec1, cli1);

        tecnicoRepository.saveAll(List.of(tec1));
        clienteRepository.saveAll(List.of(cli1));
        chamadoRepository.saveAll(List.of(c1));

    }
}
