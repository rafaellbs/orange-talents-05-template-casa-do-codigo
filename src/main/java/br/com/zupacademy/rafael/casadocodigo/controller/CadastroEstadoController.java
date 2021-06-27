package br.com.zupacademy.rafael.casadocodigo.controller;

import br.com.zupacademy.rafael.casadocodigo.dto.EstadoForm;
import br.com.zupacademy.rafael.casadocodigo.entities.Estado;
import br.com.zupacademy.rafael.casadocodigo.validation.ImpedirEstadoDuplicadoPorpais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastroEstadoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    ImpedirEstadoDuplicadoPorpais impedirEstadoDuplicadoPorpais;

    @InitBinder
    @Transactional
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(impedirEstadoDuplicadoPorpais);
    }

    @PostMapping(value = "/api/estado")
    @Transactional
    public String cadastrar(@RequestBody @Valid EstadoForm estadoForm){
        Estado estado = estadoForm.converter(manager);

        manager.persist(estado);

        return estado.toString();
    }

}
