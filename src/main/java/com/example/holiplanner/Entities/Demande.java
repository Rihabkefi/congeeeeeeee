package com.example.holiplanner.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Demande implements Serializable {
    @Id
    @GeneratedValue
    private Long id_demande;
    private Date date_envoit;
    private Date date_debut;
    private Date date_fin;

    private int periode;
    private Long idEmploye;
    @Enumerated(EnumType.STRING)
    private typeConge typeConge;
    @Lob
    private byte[] certificat;
    @Enumerated(EnumType.STRING)
    private StatutDemande statut;



}
