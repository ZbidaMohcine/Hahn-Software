package com.org.backend.repository;

import com.org.backend.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
    List<Personne> findAllByNom(String nom);

    List<Personne> findAllByPrenom(String prenom);
    List<Personne> findAllByEmail(String email);
    List<Personne> findAllByAge(int age);
}
