package com.example.lab4final.service;

import com.example.lab4final.domain.Validator.Validator;
import com.example.lab4final.repository.Repository;

import java.util.List;

public abstract class Service<E> {
    Repository<E> repository;
    Validator<E> eValidator;

    public void addElem(E e) {
        eValidator.validate(e);
        repository.save(e);
    }

    public void deleteElem(E e){
        boolean ok = false;
        for(E entity : repository.findAll()) {
            if(entity == e){
                ok = true;
            }
        }
        if(ok)
            repository.delete(e);
        else
            throw new RuntimeException("Nu exista astfel de entitate de sters!");
    }

    public Service(Repository<E> elem) {
        this.repository = elem;
    }

    public Service() {
    }

    public List<E> getAll(){
        return (List<E>) repository.findAll();
    }
}
