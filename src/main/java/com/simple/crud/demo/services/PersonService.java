package com.simple.crud.demo.services;

import com.simple.crud.demo.dtos.PersonDto;
import com.simple.crud.demo.exception.PersonNotFoundException;
import com.simple.crud.demo.models.PersonModel;
import com.simple.crud.demo.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional
    public PersonModel create(PersonModel personModel) {
        return repository.save(personModel);
    }

    public List<PersonModel> read() {
        return repository.findAll();
    }

    public PersonModel update(Long id, PersonDto personDto) {
        var personModel = repository.findById(id).orElseThrow(PersonNotFoundException::new);
        BeanUtils.copyProperties(personDto, personModel);
        return repository.save(personModel);
    }

    @Transactional
    public void delete(Long id) {
        var personModel = repository.findById(id).orElseThrow(PersonNotFoundException::new);
        repository.delete(personModel);
    }
}
