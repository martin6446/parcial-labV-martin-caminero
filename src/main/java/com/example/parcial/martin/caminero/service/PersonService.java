package com.example.parcial.martin.caminero.service;

import com.example.parcial.martin.caminero.exception.PersonNotFoundException;
import com.example.parcial.martin.caminero.model.Manager;
import com.example.parcial.martin.caminero.model.Person;
import com.example.parcial.martin.caminero.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    final private PersonRepository personRepository;
    final private PlayerService playerService;

    @Autowired
    public PersonService(PersonRepository personRepository, PlayerService playerService) {
        this.personRepository = personRepository;
        this.playerService = playerService;
    }

    public Person add(Person person) {
        personRepository.save(person);
        return person;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(int id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public Person updatePlayersList(int id, int playerId) {
        Person person = getById(id);
        Manager manager;

        if(person instanceof Manager){
            manager = (Manager) person;
            manager.getPlayers().add(playerService.getById(playerId));
        }else {
            throw new IllegalArgumentException();
        }

        return manager;
    }
}
