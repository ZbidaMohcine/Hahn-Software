package com.org.backend.controller;

import com.org.backend.dto.PersonneDto;
import com.org.backend.service.PersonneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/personnes")

public class PersonneController {
    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }
    @PostMapping("/save")
    ResponseEntity<?> savePersonne(@RequestBody PersonneDto personneDto) {
        return ResponseEntity.ok().body(personneService.save(personneDto));
    }
    @PostMapping("/findAll")
    ResponseEntity<?> findAllPersonnes() {
        return ResponseEntity.ok().body(personneService.findAll());
    }
    @GetMapping("/{id}")
    ResponseEntity<?> findPersonneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(personneService.findById(id));
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updatePersonne(@PathVariable("id") Long id, @RequestBody PersonneDto personneDto) {
        return ResponseEntity.ok().body(personneService.update(id, personneDto));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePersonne(@PathVariable("id") Long id) {
        personneService.delete(id);
        return ResponseEntity.ok().body("Personne deleted successfully");
    }
    @GetMapping("/findByNom")
    ResponseEntity<?> findByNom(@RequestParam("nom") String nom) {
        return ResponseEntity.ok().body(personneService.findByNom(nom));
    }
    @GetMapping("/findByPrenom")
    ResponseEntity<?> findByPrenom(@RequestParam("prenom") String prenom) {
        return ResponseEntity.ok().body(personneService.findByPrenom(prenom));
    }
    @GetMapping("/findByEmail")
    ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(personneService.findByEmail(email));
    }
    @GetMapping("/findByAge")
    ResponseEntity<?> findByAge(@RequestParam("age") int age) {
        return ResponseEntity.ok().body(personneService.findByAge(age));
    }

}
