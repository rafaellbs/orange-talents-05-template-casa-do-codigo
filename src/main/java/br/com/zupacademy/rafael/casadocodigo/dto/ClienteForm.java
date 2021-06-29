package br.com.zupacademy.rafael.casadocodigo.dto;

import br.com.zupacademy.rafael.casadocodigo.entities.Cliente;
import br.com.zupacademy.rafael.casadocodigo.entities.Estado;
import br.com.zupacademy.rafael.casadocodigo.entities.Pais;
import br.com.zupacademy.rafael.casadocodigo.validation.ExistId;
import br.com.zupacademy.rafael.casadocodigo.validation.UniqueValue;
import br.com.zupacademy.rafael.casadocodigo.validation.VerificaCPFouCNPJ;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteForm {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @VerificaCPFouCNPJ
    @UniqueValue(domainClass = Cliente.class, fieldName = "documento")
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ExistId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    private Long idEstado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public ClienteForm (@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
                              @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
                              @NotBlank String cidade, @NotNull Long idPais, Long idEstado, @NotBlank String telefone,
                              @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "NovoClienteRequest [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
                + documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
                + ", idPais=" + idPais + ", idEstado=" + idEstado + ", telefone=" + telefone + ", cep=" + cep + "]";
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public Cliente toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, idPais);
        Estado estado = manager.find(Estado.class, idEstado);

        Cliente cliente = new Cliente(email, nome, sobrenome,
                documento, endereco, complemento,
                cidade, pais, telefone, cep);

        cliente.setEstado(estado);

        return cliente;
    }
}
