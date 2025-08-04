package com.app.petshop.domain;

import com.app.petshop.repository.PermissionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Join;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
//@Table(name = "tutor")
public class Tutor extends User implements Serializable {
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL) // "tutor" pq é o nome do atributo na classe Pet
    private List<Pet> pets;
    private LocalDate registerDate;

    public Tutor(Long id, String name, LocalDate birthDate, String email, String password,
                 PermissionType permissionType, List<Pet> pets, LocalDate registerDate) {
        super(id, name, birthDate, email, password, permissionType);
        this.pets = pets;
        this.registerDate = registerDate;
    }
}
