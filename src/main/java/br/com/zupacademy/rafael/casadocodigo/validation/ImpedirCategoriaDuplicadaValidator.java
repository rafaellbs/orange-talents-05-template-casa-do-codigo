package br.com.zupacademy.rafael.casadocodigo.validation;

import br.com.zupacademy.rafael.casadocodigo.dto.CategoriaForm;
import br.com.zupacademy.rafael.casadocodigo.entities.Categoria;
import br.com.zupacademy.rafael.casadocodigo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ImpedirCategoriaDuplicadaValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoriaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        CategoriaForm form = (CategoriaForm) target;

        Optional<Categoria> categoria = categoriaRepository.findByNome(form.getNome());

        if (categoria.isPresent()){
            errors.rejectValue("Nome", null,"Já existe uma categoria com este nome.");
        }
    }
}
