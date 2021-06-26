package br.com.zupacademy.rafael.casadocodigo.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistIdValidator implements ConstraintValidator <ExistId, Object>{

    private String domainAttribute;
    private Class<?> klass;

    @Autowired
    private EntityManager manager;


    @Override
    public void initialize(ExistId params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + " = " + " :value");
        query.setParameter("value", value);

        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
