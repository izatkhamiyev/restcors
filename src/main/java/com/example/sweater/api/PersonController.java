package com.example.sweater.api;

import com.example.sweater.model.Person;
import com.example.sweater.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addPerson(@RequestBody Person person){

        personService.addPerson(person);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @RequestBody Person person){
        personService.updatePersonById(id, person);
    }
}
