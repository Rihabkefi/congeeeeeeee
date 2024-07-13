package com.example.holiplanner.Services;

import com.example.holiplanner.Entities.Demande;
import com.example.holiplanner.Entities.StatutDemande;

import java.util.List;

public interface DemandeService {
    public String soumettreDemande(Demande demande);
    public Demande updateDemande(Demande d , Long id_demande);
    public List<Demande> getALLDemande();
    public String mettreAJourStatutDemande(Long id_demande, StatutDemande statut);
    public List<Demande> getDemandesParEmploye(Long  idEmploye , StatutDemande statut
);
    public Demande getdBYId(Long id_demande);
    public void deleteDemande(Long numsk);
}
