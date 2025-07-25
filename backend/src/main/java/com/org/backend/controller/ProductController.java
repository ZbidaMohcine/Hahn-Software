package com.org.backend.controller;

import com.org.backend.dto.ProductDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @PostMapping("")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        // Logic to create a product
        return productDto;
    }

    @PutMapping("")
    public ProductDto updateProduct(@Valid @RequestBody ProductDto productDto) {
        // Logic to update a product
        return productDto;
    }
}
