package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Estado;
import br.com.zupacademy.rafael.casadocodigo.entities.Pais;
import br.com.zupacademy.rafael.casadocodigo.validation.ExistId;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoForm {

    @NotBlank
    private String nome;

    @NotNull
    @ExistId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    @Override
    public String toString() {
        return "EstadoForm{" +
                "nome='" + nome + '\'' +
                ", idPais=" + idPais +
                '}';
    }

    public Estado converter(EntityManager manager){
        Pais pais = manager.find(Pais.class, idPais);
        return new Estado(nome, pais);
    }
}
