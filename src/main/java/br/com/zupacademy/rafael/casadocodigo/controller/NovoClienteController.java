package br.com.zupacademy.rafael.casadocodigo.controller;

import br.com.zupacademy.rafael.casadocodigo.dto.ClienteForm;
import br.com.zupacademy.rafael.casadocodigo.entities.Cliente;
import br.com.zupacademy.rafael.casadocodigo.validation.VerificaSeEstadoPertenceAoPais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovoClienteController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private VerificaSeEstadoPertenceAoPais verificaSeEstadoPertenceAoPais;

    @InitBinder
    @Transactional
    public void initBinder (WebDataBinder webDataBinder){
        webDataBinder.addValidators(verificaSeEstadoPertenceAoPais);
    }

    @PostMapping(value = "/api/clientes")
    @Transactional
    public String cadastrar (@RequestBody @Valid ClienteForm clienteForm){
        Cliente cliente = clienteForm.toModel(manager);

        manager.persist(cliente);

        return cliente.toString();
    }
}
