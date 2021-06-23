package br.com.zupacademy.rafael.casadocodigo.validation;

import br.com.zupacademy.rafael.casadocodigo.dto.AutorDTO;
import br.com.zupacademy.rafael.casadocodigo.entities.Autor;
import br.com.zupacademy.rafael.casadocodigo.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailAutorDuplicadoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AutorDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        AutorDTO autorDTO = (AutorDTO) target;

        Optional<Autor> autor = autorRepository.findByEmail(autorDTO.getEmail());

        if (autor.isPresent()){
            errors.rejectValue("Email",null,"Email já cadastrado, tente outro.");
        }

    }
}
