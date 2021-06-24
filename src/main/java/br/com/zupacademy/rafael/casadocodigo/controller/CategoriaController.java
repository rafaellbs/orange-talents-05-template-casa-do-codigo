package br.com.zupacademy.rafael.casadocodigo.controller;

import br.com.zupacademy.rafael.casadocodigo.dto.CategoriaForm;
import br.com.zupacademy.rafael.casadocodigo.entities.Categoria;
import br.com.zupacademy.rafael.casadocodigo.repositories.CategoriaRepository;
import br.com.zupacademy.rafael.casadocodigo.validation.ImpedirCategoriaDuplicadaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ImpedirCategoriaDuplicadaValidator impedirCategoriaDuplicadaValidator;

    @PostMapping
    public String cadastrarCategoria(@Valid @RequestBody CategoriaForm form){
        Categoria categoria = form.converter();

        categoriaRepository.save(categoria);
        
        return categoria.toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(impedirCategoriaDuplicadaValidator);
    }
}
