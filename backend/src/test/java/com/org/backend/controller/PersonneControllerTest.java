package com.org.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.backend.dto.PersonneDto;
import com.org.backend.entity.Personne;
import com.org.backend.service.PersonneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonneController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PersonneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonneService personneService;

    @Autowired
    private ObjectMapper objectMapper;

    private PersonneDto personneDto;
    private Personne personne;

    @BeforeEach
    public void setUp() {
        personneDto = new PersonneDto("Ali", "Ben", "ali.ben@gmail.com", 30);


        personne = new Personne();
        personne.setId(1L);
        personne.setNom("Ali");
        personne.setPrenom("Ben");
        personne.setEmail("ali.ben@example.com");
        personne.setAge(30);
    }

    @Test
    public void testSavePersonne() throws Exception {
        Mockito.when(personneService.save(any(PersonneDto.class))).thenReturn(personne);

        mockMvc.perform(post("/api/v1/personnes/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personneDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testFindAllPersonnes() throws Exception {
        Mockito.when(personneService.findAll()).thenReturn(Collections.singletonList(personne));

        mockMvc.perform(get("/api/v1/personnes/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testFindById() throws Exception {
        Mockito.when(personneService.findById(1L)).thenReturn(personne);

        mockMvc.perform(get("/api/v1/personnes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ali.ben@example.com"));
    }

    @Test
    public void testUpdatePersonne() throws Exception {
        Mockito.when(personneService.update(Mockito.eq(1L), any(PersonneDto.class))).thenReturn(personne);

        mockMvc.perform(put("/api/v1/personnes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personneDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    public void testDeletePersonne() throws Exception {
        mockMvc.perform(delete("/api/v1/personnes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Personne deleted successfully"));
    }

    @Test
    public void testFindByNom() throws Exception {
        Mockito.when(personneService.findByNom("Ali")).thenReturn(Collections.singletonList(personne));

        mockMvc.perform(get("/api/v1/personnes/findByNom?nom=Ali"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Ali"));
    }
}
