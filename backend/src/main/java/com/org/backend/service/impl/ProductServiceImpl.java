package com.org.backend.service.impl;

import com.org.backend.dto.CodeError;
import com.org.backend.dto.ProductDto;
import com.org.backend.entity.Product;
import com.org.backend.exception.ProductNoValidNameException;
import com.org.backend.exception.ProductNotFoundException;
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
        if (productRepository.existsByName(dto.name())) {
            throw new ProductNoValidNameException(CodeError.NAME_ALREADY_EXISTS);
        }
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        Product saved = productRepository.save(product);
        return new ProductDto(saved.getId(), saved.getName(), saved.getPrice());
    }

    @Override
    public ProductDto updateProduct(ProductDto dto) {
        Product existingProduct = productRepository.findById(dto.id())
                .orElseThrow(() -> new ProductNotFoundException(CodeError.PRODUCT_NOT_FOUND));
        if (productRepository.existsByName(dto.name())) {
            throw new ProductNoValidNameException(CodeError.NAME_ALREADY_EXISTS);
        }
        existingProduct.setName(dto.name());
        existingProduct.setPrice(dto.price());
        Product updatedProduct = productRepository.save(existingProduct);
        return new ProductDto(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getPrice());
    }
} 