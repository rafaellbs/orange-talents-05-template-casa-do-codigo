package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Pais;
import br.com.zupacademy.rafael.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisForm {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    @Override
    public String toString() {
        return "PaisForm{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public Pais converter(){
        return new Pais(nome);
    }
}
