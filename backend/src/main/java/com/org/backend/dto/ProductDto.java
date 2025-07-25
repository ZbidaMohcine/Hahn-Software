package com.org.backend.dto;

import jakarta.validation.constraints.*;

public record ProductDto(
        Long id,

        @NotNull(message = "Name cannot be null")
        @NotBlank(message = "Name cannot be blank")
        @Pattern(regexp = "^\\D*$", message = "Name must not contain numbers")
        String name,

        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.0", inclusive = true, message = "Price must be positive")
        Double price
) {}
