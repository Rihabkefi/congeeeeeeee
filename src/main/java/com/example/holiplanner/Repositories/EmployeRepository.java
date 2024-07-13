package com.example.holiplanner.Repositories;

import com.example.holiplanner.Entities.Demande;
import com.example.holiplanner.Entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe,Long> {
}
