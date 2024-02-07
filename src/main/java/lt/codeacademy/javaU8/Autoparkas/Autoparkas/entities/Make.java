package lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Make {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;


    public Make() {
    }

    public Make(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
