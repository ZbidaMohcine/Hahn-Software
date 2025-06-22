package com.org.backend.service.impl;

import com.org.backend.dto.PersonneDto;
import com.org.backend.entity.Personne;
import com.org.backend.exception.PersonneNotFoundException;
import com.org.backend.repository.PersonneRepository;
import com.org.backend.service.PersonneService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
        Personne newPersonne = new Personne();
        newPersonne.setNom(personne.getNom());
        newPersonne.setPrenom(personne.getPrenom());
        newPersonne.setEmail(personne.getEmail());
        newPersonne.setAge(personne.getAge());
        return personneRepository.save(newPersonne);
    }

    @Override
    public Personne findById(Long id) {
        return personneRepository.findById(id)
                .orElseThrow(() -> new PersonneNotFoundException("Personne not found with id: " + id));
    }

    @Override
    public Personne update(Long id, PersonneDto personne) {
        Personne existingPersonne = findById(id);
        existingPersonne.setNom(personne.getNom());
        existingPersonne.setPrenom(personne.getPrenom());
        existingPersonne.setEmail(personne.getEmail());
        existingPersonne.setAge(personne.getAge());
        return personneRepository.save(existingPersonne);
    }

    @Override
    public void delete(Long id) {
        if (!personneRepository.existsById(id)) {
            throw new PersonneNotFoundException("Personne not found with id: " + id);
        }
        personneRepository.deleteById(id);

    }

    @Override
    public List<Personne> findByNom(String nom) {
        return personneRepository.findAllByNom(nom);
    }

    @Override
    public List<Personne> findByPrenom(String prenom) {
        return personneRepository.findAllByPrenom(prenom);
    }

    @Override
    public List<Personne> findByEmail(String email) {
        return personneRepository.findAllByEmail(email);
    }

    @Override
    public List<Personne> findByAge(int age) {
        return personneRepository.findAllByAge(age);
    }
}
