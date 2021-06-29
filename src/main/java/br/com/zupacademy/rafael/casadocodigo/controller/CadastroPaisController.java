package br.com.zupacademy.rafael.casadocodigo.controller;

import br.com.zupacademy.rafael.casadocodigo.dto.PaisForm;
import br.com.zupacademy.rafael.casadocodigo.entities.Pais;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastroPaisController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/api/pais")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PaisForm paisForm) {
        Pais pais = paisForm.converter();

        manager.persist(pais);

        return ResponseEntity.ok(paisForm.toString());
    }
}
