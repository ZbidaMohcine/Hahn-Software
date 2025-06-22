package com.org.backend.service;

import com.org.backend.dto.PersonneDto;
import com.org.backend.entity.Personne;

import java.util.List;

public interface PersonneService {

     public List<Personne> findAll();
     public Personne save(PersonneDto personne);
     public Personne findById(Long id);
     public Personne update(Long id, PersonneDto personne);
     public void delete(Long id);
     public List<Personne> findByNom(String nom);
    public List<Personne> findByPrenom(String prenom);
    public List<Personne> findByEmail(String email);
    public List<Personne> findByAge(int age);

}
