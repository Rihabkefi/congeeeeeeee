package com.example.holiplanner.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Employe implements Serializable {
    @Id
    @GeneratedValue
    private Long idEmploye;
   private int   soldeCong ;
   @OneToMany
   private List<Demande> demandeconge;

}
