package se.systementor.blacklist.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Person")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;    
    private String email;    
    private String name;
    private String group;



    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    private LocalDateTime created = LocalDateTime.now();
    private boolean isOk;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    public boolean isOk() {
        return isOk;
    }
    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }
}
