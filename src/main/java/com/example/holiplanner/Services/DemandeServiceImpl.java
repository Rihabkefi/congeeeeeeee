package com.example.holiplanner.Services;

import com.example.holiplanner.Entities.Demande;
import com.example.holiplanner.Entities.Employe;
import com.example.holiplanner.Entities.StatutDemande;
import com.example.holiplanner.Repositories.DemandeRepository;
import com.example.holiplanner.Repositories.EmployeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service

public class DemandeServiceImpl implements DemandeService{
    public EmployeRepository employeeRepository;
    public DemandeRepository demandeRepository;

    @Override
    public String soumettreDemande(Demande demande) {
        Employe employe = employeeRepository.findById(demande.getIdEmploye()).orElse(null);
        if (employe == null) {
            return "Employé non trouvé";
        }

        int dureeDemande = demande.getPeriode();

        if (employe.getSoldeCong() < dureeDemande) {
            return "Solde de congé insuffisant. Vous avez " + employe.getSoldeCong() + " jours de congé restants.";
        }

        demande.setStatut(StatutDemande.EN_ATTENTE);

        demandeRepository.save(demande);
        return "Demande de congé soumise avec succès.";
    }

    @Override
    public Demande updateDemande(Demande d, Long id_demande) {
        Optional<Demande> existingDemande = demandeRepository.findById(id_demande);
        if (existingDemande.isPresent()) {
            return demandeRepository.save(d);
        } else {
            throw new EntityNotFoundException("Demande not found with id: " + id_demande);
        }
    }

    @Override
    public List<Demande> getALLDemande() {
        return demandeRepository.findAll();
    }

    @Override
    public String mettreAJourStatutDemande(Long id_demande, StatutDemande statut) {
        Demande demande = demandeRepository.findById(id_demande).orElse(null);
        if (demande == null) {
            return "Demande non trouvée";
        }

        if (statut == StatutDemande.ACCEPTEE) {
            Employe employe = employeeRepository.findById(demande.getIdEmploye()).orElse(null);
            if (employe == null) {
                return "Employé non trouvé";
            }

            int dureeDemande = demande.getPeriode();
            int nouveauSolde = employe.getSoldeCong() - dureeDemande;
            if (nouveauSolde < 0) {
                return "Solde de congé insuffisant. Vous avez " + employe.getSoldeCong() + " jours de congé restants.";
            }

            employe.setSoldeCong(nouveauSolde);
            employeeRepository.save(employe);
        }

        demande.setStatut(statut);
        demandeRepository.save(demande);

        return "Statut de la demande mis à jour avec succès";
    }

    @Override
    public List<Demande> getDemandesParEmploye(Long   idEmploye , StatutDemande statut
) {
        return demandeRepository.findByIdEmployeAndStatut(idEmploye , statut) ;
    }

    @Override
    public Demande getdBYId(Long id_demande) {
        return demandeRepository.findById(id_demande).orElse(null);
    }

    @Override
    public void deleteDemande(Long id_demande ) {
        demandeRepository.deleteById(id_demande); ;

    }
}
