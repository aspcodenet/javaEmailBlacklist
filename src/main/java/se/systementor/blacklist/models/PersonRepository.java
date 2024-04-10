package se.systementor.blacklist.models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {
    public Optional<Person> findByGroupAndEmail(String group,String email); 
    public List<Person> findAllByGroup(String group); 
}
