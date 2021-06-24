package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public Categoria converter(){
        return new Categoria(nome);
    }
}
