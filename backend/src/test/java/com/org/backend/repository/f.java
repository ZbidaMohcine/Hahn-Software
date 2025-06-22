package com.org.backend.repository;

import com.org.backend.entity.Personne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonneRepositoryTest {

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    void testFindAllByNom() {
        // Arrange
        Personne personne1 = new Personne(null, "John", "Doe", "john.doe@example.com", 30);
        Personne personne2 = new Personne(null, "John", "Smith", "john.smith@example.com", 25);
        personneRepository.save(personne1);
        personneRepository.save(personne2);

        // Act
        List<Personne> result = personneRepository.findAllByNom("John");

        // Assert
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNom());
        assertEquals("John", result.get(1).getNom());
    }

    @Test
    void testFindAllByPrenom() {
        // Arrange
        Personne personne = new Personne(null, "Jane", "Doe", "jane.doe@example.com", 28);
        personneRepository.save(personne);

        // Act
        List<Personne> result = personneRepository.findAllByPrenom("Jane");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Jane", result.get(0).getPrenom());
    }

    @Test
    void testFindAllByEmail() {
        // Arrange
        Personne personne = new Personne(null, "John", "Doe", "john.doe@example.com", 30);
        personneRepository.save(personne);

        // Act
        List<Personne> result = personneRepository.findAllByEmail("john.doe@example.com");

        // Assert
        assertEquals(1, result.size());
        assertEquals("john.doe@example.com", result.get(0).getEmail());
    }

    @Test
    void testFindAllByAge() {
        // Arrange
        Personne personne = new Personne(null, "John", "Doe", "john.doe@example.com", 30);
        personneRepository.save(personne);

        // Act
        List<Personne> result = personneRepository.findAllByAge(30);

        // Assert
        assertEquals(1, result.size());
        assertEquals(30, result.get(0).getAge());
    }
}