package br.com.zupacademy.rafael.casadocodigo.controller;

import br.com.zupacademy.rafael.casadocodigo.dto.AutorDTO;
import br.com.zupacademy.rafael.casadocodigo.entities.Autor;
import br.com.zupacademy.rafael.casadocodigo.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public String cadastrarAutor (@Valid @RequestBody AutorDTO autorDTO){
        Autor autor = autorDTO.converter();
        autorRepository.save(autor);
        return autor.toString();

    }
}
