package se.systementor.blacklist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.systementor.DTO.CreatePersonDTO;
import se.systementor.DTO.UpdatePersonDTO;
import se.systementor.blacklist.models.Person;
import se.systementor.blacklist.services.PersonService;

@RestController
public class BlacklistController{
    @Autowired
    private PersonService personService;

    @GetMapping("/api/{group}/blacklistcheck/{email}")
    public ResponseEntity<BlacklistStatus> Get(@PathVariable String group, @PathVariable String email){
        BlacklistStatus status = new BlacklistStatus();

        if(personService.IsEmailBlacklisted(group, email)){
            status.setOk(false);
            status.setStatusText("Blacklisted");
        }else{
            status.setOk(true);
            status.setStatusText("OK");

        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }    

    @GetMapping("/api/{group}/blacklist")
    public ResponseEntity<List<Person>> GetAll(@PathVariable String group){
        return new ResponseEntity<>(personService.GetAll(group), HttpStatus.OK);
    }
    @PostMapping("/api/{group}/blacklist")
    public ResponseEntity<Person> New(@PathVariable String group, @RequestBody CreatePersonDTO product){
        try{
            var newCreated = personService.add(group, product);
            return ResponseEntity.ok(newCreated); // mer REST ful = created (204) samt url till produkten
        }
        catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/api/{group}/blacklist/{email}")
    public ResponseEntity<Person> Update(@PathVariable String group, @PathVariable String email, @RequestBody UpdatePersonDTO updateDTO){
        try{
            personService.update(group, email,updateDTO);
            return ResponseEntity.noContent().build(); // mer REST ful = created (204) samt url till produkten
        }
        catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }


/*
@GetMapping("/api/products")
    public ResponseEntity<List<Product>> GetAll(){
        return new ResponseEntity<>(productService.GetAll(), HttpStatus.OK);
    }
    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> Get(@PathVariable UUID id){
        Optional<Product> product = productService.getById(id);
        if(product.isPresent()) return ResponseEntity.ok(product.get());
        return  ResponseEntity.notFound().build();
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> Update(@PathVariable UUID id, @RequestBody Product product){
        boolean status = productService.update(product);
        if(status == true)
            return ResponseEntity.ok(product);
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/api/products")
    public ResponseEntity<Product> New(@RequestBody Product product){
        var newCreated = productService.add(product);
        return ResponseEntity.ok(newCreated); // mer REST ful = created (204) samt url till produkten
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<String> Delete(@PathVariable UUID id){
        boolean status = productService.deleteById(id);
        if(status == true)
            return ResponseEntity.ok("Deleted");
        else
            return ResponseEntity.badRequest().build();
    }

*/ 


}
