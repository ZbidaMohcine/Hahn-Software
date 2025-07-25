package com.org.backend.service;

import com.org.backend.dto.CodeError;
import com.org.backend.dto.ProductDto;
import com.org.backend.entity.Product;
import com.org.backend.exception.ProductNoValidNameException;
import com.org.backend.exception.ProductNotFoundException;
import com.org.backend.repository.ProductRepository;
import com.org.backend.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testCreateProduct() {
        ProductDto dto = new ProductDto(1L, "Test Product", 100.0);

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Test Product");
        savedProduct.setPrice(100.0);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductDto result = productService.createProduct(dto);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productCaptor.capture());

        Product captured = productCaptor.getValue();
        assertEquals("Test Product", captured.getName());
        assertEquals(100.0, captured.getPrice());

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Test Product", result.name());
        assertEquals(100.0, result.price());
    }
    @Test
    void testCreateProductWithExistingName_shouldThrowException() {
        ProductDto dto = new ProductDto(null, "Test Product", 100.0);

        when(productRepository.existsByName("Test Product")).thenReturn(true);

        ProductNoValidNameException exception = assertThrows(ProductNoValidNameException.class,
                () -> productService.createProduct(dto));

        assertEquals(CodeError.NAME_ALREADY_EXISTS.getMessage(), exception.getMessage());
    }
    @Test
    void testUpdateProduct() {
        ProductDto dto = new ProductDto(1L, "Test Product", 100.0);
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Product");
        existingProduct.setPrice(50.0);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);
        ProductDto result = productService.updateProduct(dto);
        assertNotNull(result);
        assertEquals(1L, result.id());
    }
    @Test
    void testUpdateProductNotFound_shouldThrowException() {
        ProductDto dto = new ProductDto(1L, "Test Product", 100.0);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                () -> productService.updateProduct(dto));

        assertEquals(CodeError.PRODUCT_NOT_FOUND.getMessage(), exception.getMessage());
    }
    @Test
    void testUpdateProductWithExistingName_shouldThrowException() {
        ProductDto dto = new ProductDto(1L, "Test Product", 100.0);
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Product");
        existingProduct.setPrice(50.0);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(existingProduct));
        when(productRepository.existsByName("Test Product")).thenReturn(true);

        ProductNoValidNameException exception = assertThrows(ProductNoValidNameException.class,
                () -> productService.updateProduct(dto));

        assertEquals(CodeError.NAME_ALREADY_EXISTS.getMessage(), exception.getMessage());
    }
} 