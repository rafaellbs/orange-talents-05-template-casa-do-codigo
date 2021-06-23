package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorDTO {

    @NotBlank
    @Size(min = 3, max = 30)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    public AutorDTO(@NotBlank @Size(min = 3, max = 30)String nome,@NotBlank @Email String email,
                    @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor converter(){
        return new Autor(this.nome, this.email, this.descricao);
    }
}