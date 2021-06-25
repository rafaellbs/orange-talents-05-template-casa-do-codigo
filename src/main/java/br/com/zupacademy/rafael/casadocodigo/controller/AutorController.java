package br.com.zupacademy.rafael.casadocodigo.controller;

import br.com.zupacademy.rafael.casadocodigo.dto.AutorForm;
import br.com.zupacademy.rafael.casadocodigo.entities.Autor;
import br.com.zupacademy.rafael.casadocodigo.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public String cadastrarAutor (@Valid @RequestBody AutorForm autorForm){
        Autor autor = autorForm.converter();
        autorRepository.save(autor);
        return autor.toString();

    }

}
