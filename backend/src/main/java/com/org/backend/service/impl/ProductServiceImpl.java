package com.org.backend.service.impl;

import com.org.backend.dto.ProductDto;
import com.org.backend.entity.Product;
import com.org.backend.repository.ProductRepository;
import com.org.backend.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        Product saved = productRepository.save(product);
        ProductDto result = new ProductDto();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setPrice(saved.getPrice());
        return result;
    }
} 