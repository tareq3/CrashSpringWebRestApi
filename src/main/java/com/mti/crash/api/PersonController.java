package com.mti.crash.api;

import com.mti.crash.model.Person;
import com.mti.crash.service.PersonService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/ v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID uuid) {
        return personService.getPersonById(uuid).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID uuid) {
        personService.deletePerson(uuid);
    }

    @PutMapping(path = "{id}")
    public void updatePersonById(@PathVariable("id") UUID uuid, @Valid @NotNull @RequestBody Person person){
        personService.updatePerson(uuid,person);
    }
}
