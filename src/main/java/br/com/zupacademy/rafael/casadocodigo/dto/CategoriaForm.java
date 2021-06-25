package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Categoria;
import br.com.zupacademy.rafael.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    public String getNome() {
        return nome;
    }

    public Categoria converter(){
        return new Categoria(nome);
    }
}
