package lt.codeacademy.javaU8.Autoparkas.Autoparkas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRoles name;

    public Role() {
    }

    public Role(EnumRoles name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumRoles getName() {
        return name;
    }

    public void setName(EnumRoles name) {
        this.name = name;
    }
}
