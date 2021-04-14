package com.example.parcial.martin.caminero.controller;

import com.example.parcial.martin.caminero.model.Person;
import com.example.parcial.martin.caminero.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.add(person);
    }

    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id){
        return personService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void removePerson(@PathVariable int id){
        personService.delete(personService.getById(id));
    }

    @PutMapping("/{id}/players/{playerId}")
    public Person addPlayersToManager(@PathVariable int id, @PathVariable int playerId){
        return personService.updatePlayersList(id,playerId);
    }

}
