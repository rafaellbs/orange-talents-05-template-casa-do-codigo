package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Livro;

public class FormatLivroResponse {

    private Long id;
    private String nome;

    public FormatLivroResponse(Livro livro){
        this.id = livro.getId();
        this.nome = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "LivroResponse{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
