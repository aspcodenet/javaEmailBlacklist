package se.systementor.blacklist.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.systementor.DTO.CreatePersonDTO;
import se.systementor.DTO.UpdatePersonDTO;
import se.systementor.blacklist.models.Person;
import se.systementor.blacklist.models.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public boolean IsEmailBlacklisted(String group, String email){
        var p = personRepository.findByGroupAndEmail(group,email);
        return p.isPresent() && p.get().isOk() == false;
    }

    public List<Person> GetAll(String group) {
        return personRepository.findAllByGroup(group);
    }

    public Person add(String group, CreatePersonDTO personDTO) throws Exception {
        if(personRepository.findByGroupAndEmail(group, personDTO.getEmail()).isPresent()){
            throw new Exception("Already existing");
        }

        var person = new Person();
        person.setId(0);
        person.setGroup(group);
        person.setCreated(LocalDateTime.now());
        person.setName(personDTO.getName());
        person.setEmail((personDTO.getEmail()));
        person.setOk(personDTO.getIsOk());
        return personRepository.save(person);
    }

    public void update(String group, String email, UpdatePersonDTO personDTO) throws Exception {
        var existing = personRepository.findByGroupAndEmail(group,email);
        if(!existing.isPresent()){
            throw new Exception("Not found");
        }
        var obj = existing.get();
        obj.setName(personDTO.getName());
        obj.setOk(personDTO.getIsOk());
        personRepository.save(obj);
    }
    
}
