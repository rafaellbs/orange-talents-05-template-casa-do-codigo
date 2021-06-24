package br.com.zupacademy.rafael.casadocodigo.repositories;

import br.com.zupacademy.rafael.casadocodigo.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    Optional<Categoria> findByNome(String nome);

}
