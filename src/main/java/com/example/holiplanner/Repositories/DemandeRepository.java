package com.example.holiplanner.Repositories;

import com.example.holiplanner.Entities.Demande;
import com.example.holiplanner.Entities.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
    List<Demande> findByIdEmployeAndStatut(long idEmploye , StatutDemande statut);

}
