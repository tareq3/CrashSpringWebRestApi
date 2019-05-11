package com.mti.crash.api;

import com.mti.crash.domain.Person;
import com.mti.crash.service.PersonService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
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

    @PutMapping()
    public void updatePerson(@RequestParam(name = "id") UUID uuid, @Valid @NotNull @RequestBody Person person){
        personService.updatePerson(uuid, person);
    }

    @DeleteMapping()
    public void deletePerson(@RequestParam(name = "id") UUID uuid) {
        personService.deletePerson(uuid);
    }
}
