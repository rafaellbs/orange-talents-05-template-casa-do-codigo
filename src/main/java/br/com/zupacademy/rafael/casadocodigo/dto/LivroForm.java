package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Autor;
import br.com.zupacademy.rafael.casadocodigo.entities.Categoria;
import br.com.zupacademy.rafael.casadocodigo.entities.Livro;
import br.com.zupacademy.rafael.casadocodigo.validation.ExistId;
import br.com.zupacademy.rafael.casadocodigo.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroForm {

    @NotBlank
    @UniqueValue(fieldName = "titulo", domainClass = Livro.class)
    private String titulo;

    @NotBlank @Size(max = 500)
    private String resumo;

    @NotBlank
    private String sumario;

    @NotNull @Min(20)
    private BigDecimal preco;

    @NotNull @Min(100)
    private Integer numPaginas;

    @NotBlank @UniqueValue(fieldName = "isbn", domainClass = Livro.class)
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    @ExistId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @NotNull
    @ExistId(domainClass = Autor.class, fieldName = "id")
    private Long idAutor;

    public LivroForm(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
                     @NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numPaginas, @NotBlank String isbn,
                     @NotNull @Future LocalDate dataPublicacao, @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPaginas = numPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }


    @Override
    public String toString() {
        return "LivroForm [titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario + ", preco=" + preco
                + ", numPaginas=" + numPaginas + ", isbn=" + isbn + ", dataPublicacao=" + dataPublicacao
                + ", idCategoria=" + idCategoria + ", idAutor=" + idAutor + "]";
    }

    public Livro converter(EntityManager manager) {
        Autor autor = manager.find(Autor.class, idAutor);
        Categoria categoria = manager.find(Categoria.class, idCategoria);

        return new Livro(titulo, resumo, sumario, preco, numPaginas, isbn, dataPublicacao, categoria, autor);
    }
}
