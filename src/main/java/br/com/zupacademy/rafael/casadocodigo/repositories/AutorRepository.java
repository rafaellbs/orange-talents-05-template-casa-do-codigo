package br.com.zupacademy.rafael.casadocodigo.repositories;

import br.com.zupacademy.rafael.casadocodigo.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByEmail(String email);
}
