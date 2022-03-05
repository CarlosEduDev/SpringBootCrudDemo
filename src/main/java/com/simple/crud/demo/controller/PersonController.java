package com.simple.crud.demo.controller;

import com.simple.crud.demo.dtos.PersonDto;
import com.simple.crud.demo.models.PersonModel;
import com.simple.crud.demo.services.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    public ResponseEntity<PersonModel> create(@RequestBody @Valid PersonDto personDto) {
        var personModel = new PersonModel();
        BeanUtils.copyProperties(personDto, personModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(personModel));
    }

    @GetMapping
    public ResponseEntity<List<PersonModel>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(service.read());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonModel> update(@PathVariable("id") Long id, @RequestBody @Valid PersonDto personDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, personDto));
    }

    //Nesse caso o que eu defino no generics, definir void pq queria dar um status code OK, mas sem extamente um retorno
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
