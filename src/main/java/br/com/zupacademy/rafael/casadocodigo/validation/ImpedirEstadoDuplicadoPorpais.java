package br.com.zupacademy.rafael.casadocodigo.validation;

import br.com.zupacademy.rafael.casadocodigo.dto.EstadoForm;
import br.com.zupacademy.rafael.casadocodigo.entities.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class ImpedirEstadoDuplicadoPorpais implements Validator {

    @Autowired
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return EstadoForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        EstadoForm request = (EstadoForm) target;

        Pais pais = manager.find(Pais.class, request.getIdPais());

        Query query = manager.createQuery("select e from Estado e where e.nome = :nome and e.pais = :pais");
        query.setParameter("nome", request.getNome());
        query.setParameter("pais", pais);

        List<?> resultList = query.getResultList();

        if (resultList.size() > 0) {
            errors.rejectValue("nome", null, "Este estado já existe neste País");
        }
    }


}
