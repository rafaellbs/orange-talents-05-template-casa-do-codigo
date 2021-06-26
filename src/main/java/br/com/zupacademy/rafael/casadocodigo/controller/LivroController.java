package br.com.zupacademy.rafael.casadocodigo.controller;

import br.com.zupacademy.rafael.casadocodigo.dto.LivroForm;
import br.com.zupacademy.rafael.casadocodigo.dto.FormatLivroResponse;
import br.com.zupacademy.rafael.casadocodigo.entities.Livro;
import br.com.zupacademy.rafael.casadocodigo.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public String criar(@RequestBody @Valid LivroForm form) {
        Livro livro = form.converter(manager);

        manager.persist(livro);

        return form.toString();
    }

    @GetMapping
    public List<FormatLivroResponse> listar(){
        List<Livro> livros = (List<Livro>) livroRepository.findAll();

        List<FormatLivroResponse> livrosFormatados = livros.stream().map(FormatLivroResponse::new).collect(Collectors.toList());

        return livrosFormatados;
    }
}
