package com.org.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonneDto {
    private String nom;
    private String prenom;
    private String email;
    private int age;
}
