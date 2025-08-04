package com.app.petshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "pet")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    @Enumerated(EnumType.STRING)
    private PetType type;
    private int age;
    @ManyToOne
    @JoinColumn(name = "tutor_id") // cria a foreign key na tabela PET apontando para TUTOR
    private Tutor tutor;
}
