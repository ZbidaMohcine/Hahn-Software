package com.org.backend.service.impl;

import com.org.backend.dto.CodeError;
import com.org.backend.dto.PersonneDto;
import com.org.backend.entity.Personne;
import com.org.backend.exception.PersonneNotFoundException;
import com.org.backend.repository.PersonneRepository;
import com.org.backend.service.PersonneService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Service
@Slf4j
public class PersonneServiceImpl implements PersonneService {
    private final PersonneRepository personneRepository;

    public PersonneServiceImpl(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @Override
    public List<Personne> findAll() {
        return personneRepository.findAll();
    }

    @Override
    public Personne save(PersonneDto personne) {
        log.info("Saving personne: {}", personne);
        Personne newPersonne = new Personne();
        newPersonne.setNom(personne.nom());
        newPersonne.setPrenom(personne.prenom());
        newPersonne.setEmail(personne.email());
        newPersonne.setAge(personne.age());
        return personneRepository.save(newPersonne);
    }

    @Override
    public Personne findById(Long id) {
        log.info("Finding personne by id: {}", id);
        return personneRepository.findById(id)
                .orElseThrow(() -> new PersonneNotFoundException(CodeError.PERSONNE_NOT_FOUND));
    }

    @Override
    public Personne update(Long id, PersonneDto personne) {
        log.info("Updating personne: {}", personne);
        Personne existingPersonne = findById(id);
        existingPersonne.setNom(personne.nom());
        existingPersonne.setPrenom(personne.prenom());
        existingPersonne.setEmail(personne.email());
        existingPersonne.setAge(personne.age());
        return personneRepository.save(existingPersonne);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting personne by id: {}", id);
        if (!personneRepository.existsById(id)) {
            throw new PersonneNotFoundException(CodeError.PERSONNE_NOT_FOUND);
        }
        personneRepository.deleteById(id);

    }

    @Override
    public List<Personne> findByNom(String nom) {
        log.info("Finding personne by nom: {}", nom);
        return personneRepository.findAllByNom(nom);
    }

    @Override
    public List<Personne> findByPrenom(String prenom) { 
        log.info("Finding personne by prenom: {}", prenom);
        return personneRepository.findAllByPrenom(prenom);
    }

    @Override
    public List<Personne> findByEmail(String email) {   
        log.info("Finding personne by email: {}", email);
        return personneRepository.findAllByEmail(email);
    }

    @Override
    public List<Personne> findByAge(int age) {
        log.info("Finding personne by age: {}", age);
        return personneRepository.findAllByAge(age);
    }
}
