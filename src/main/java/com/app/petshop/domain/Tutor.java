package com.app.petshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Tutor extends User implements Serializable {
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL) // "tutor" no mappedBy pq é o nome do atributo na classe Pet
    private List<Pet> pets;
    private LocalDate registerDate;

    public Tutor(Long id, String name, LocalDate birthDate, String email, String password,
                 PermissionType permissionType, List<Pet> pets, LocalDate registerDate) {
        super(id, name, birthDate, email, password, permissionType);
        this.pets = pets;
        this.registerDate = registerDate;
    }
}
