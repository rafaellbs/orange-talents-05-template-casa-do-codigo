package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Autor;
import br.com.zupacademy.rafael.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorForm {

    @NotBlank
    @Size(min = 3, max = 30)
    private String nome;

    @NotBlank
    @Email
    @UniqueValue(fieldName = "email", domainClass = Autor.class)
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    public AutorForm(@NotBlank @Size(min = 3, max = 30)String nome, @NotBlank @Email String email,
                     @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public Autor converter(){
        return new Autor(this.nome, this.email, this.descricao);
    }
}
