package com.example.holiplanner.Controllers;

import com.example.holiplanner.Entities.Demande;
import com.example.holiplanner.Entities.StatutDemande;
import com.example.holiplanner.Services.DemandeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor

public class DemandeController {

     DemandeService demandeService ;


        @PostMapping("/soumettredemande")
        public String soumettreDemande(@RequestBody Demande d) {

            return demandeService.soumettreDemande(d);
        }

    @PutMapping("/{id}")
    public ResponseEntity<Demande> updateDemande(@RequestBody Demande demande, @PathVariable("id") Long id_demande) {
        try {
            Demande updatedDemande = demandeService.updateDemande(demande, id_demande);
            return new ResponseEntity<>(updatedDemande, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/statut")
    public String mettreAJourStatutDemande(@PathVariable Long id, @RequestBody StatutDemande statut) {
        return demandeService.mettreAJourStatutDemande(id, statut);
    }

    @GetMapping("/employe/{id_employe}/{statut}")
    public List<Demande> getDemandesParEmploye(@PathVariable Long id_employe , @PathVariable StatutDemande statut) {
        return demandeService.getDemandesParEmploye(id_employe , statut);
    }

        @GetMapping("/getAllDemande")
        public List<Demande> getAllDemandes() {
            return demandeService.getALLDemande();
        }

        @GetMapping("/getDemandeById/{id}")
        public Demande getDemandeById(@PathVariable Long id) {
            return demandeService.getdBYId(id);
        }

        @DeleteMapping("/deleteDemande/{id}")
        public void deleteDemande(@PathVariable Long id) {
            demandeService.deleteDemande(id);
        }
    }



