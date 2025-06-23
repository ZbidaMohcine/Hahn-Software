package com.org.backend.service;

import com.org.backend.dto.PersonneDto;
import com.org.backend.entity.Personne;
import com.org.backend.exception.PersonneNotFoundException;
import com.org.backend.repository.PersonneRepository;
import com.org.backend.service.impl.PersonneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonneServiceTest {

    @Mock
    private PersonneRepository personneRepository;

    @InjectMocks
    private PersonneServiceImpl personneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Personne> personnes = List.of(new Personne(1L, "John", "Doe", "john.doe@example.com", 30));
        when(personneRepository.findAll()).thenReturn(personnes);

        List<Personne> result = personneService.findAll();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getNom());
        verify(personneRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Personne personne = new Personne(1L, "John", "Doe", "john.doe@example.com", 30);
        when(personneRepository.findById(1L)).thenReturn(Optional.of(personne));

        Personne result = personneService.findById(1L);

        assertNotNull(result);
        assertEquals("John", result.getNom());
        verify(personneRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(personneRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PersonneNotFoundException.class, () -> personneService.findById(1L));
        verify(personneRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        PersonneDto personneDto = new PersonneDto("John", "Doe", "john.doe@example.com", 30);
        Personne personne = new Personne(null, "John", "Doe", "john.doe@example.com", 30);
        Personne savedPersonne = new Personne(1L, "John", "Doe", "john.doe@example.com", 30);

        when(personneRepository.save(any(Personne.class))).thenReturn(savedPersonne);

        Personne result = personneService.save(personneDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(personneRepository, times(1)).save(any(Personne.class));
    }

    @Test
    void testDelete() {
        Long id = 1L;
        when(personneRepository.existsById(id)).thenReturn(true);
        doNothing().when(personneRepository).deleteById(id);

        personneService.delete(id);

        verify(personneRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindByNom() {
        List<Personne> personnes = Arrays.asList(new Personne(1L, "John", "Doe", "john.doe@example.com", 30));
        when(personneRepository.findAllByNom("John")).thenReturn(personnes);

        List<Personne> result = personneService.findByNom("John");

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getNom());
        verify(personneRepository, times(1)).findAllByNom("John");
    }
}