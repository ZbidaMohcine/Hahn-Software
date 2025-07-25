package com.org.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.backend.dto.ProductDto;
import com.org.backend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateProduct() throws Exception {
        ProductDto dto = new ProductDto(1L, "Test Product", 100.0);
        when(productService.createProduct(any(ProductDto.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/product")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void testCreateProductWithEmpthyName_shouldReturnBadRequest() throws Exception {
        ProductDto dto = new ProductDto(null, "", 100.0);

        mockMvc.perform(post("/api/v1/product")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.name").value("Name cannot be blank"));
    }
    @Test
    void testCreateProductWithInvalidName_shouldReturnBadRequest() throws Exception {
        ProductDto dto = new ProductDto(null, "Test123", 100.0);

        mockMvc.perform(post("/api/v1/product")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.name").value("Name must not contain numbers"));
    }
    @Test
    void testCreateProductWithNullPrice_shouldReturnBadRequest() throws Exception {
        ProductDto dto = new ProductDto(null, "Test Product", null);

        mockMvc.perform(post("/api/v1/product")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.price").value("Price cannot be null"));
    }
    @Test
    void testCreateProductWithNegativePrice_shouldReturnBadRequest() throws Exception {
        ProductDto dto = new ProductDto(null, "Test Product", -50.0);

        mockMvc.perform(post("/api/v1/product")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.price").value("Price must be positive"));
    }
    @Test
    void testUpdateProduct() throws Exception {
        ProductDto dto = new ProductDto(1L, "Updated Product", 150.0);
        when(productService.updateProduct(any(ProductDto.class))).thenReturn(dto);

        mockMvc.perform(put("/api/v1/product")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.price").value(150.0));
    }
    @Test
    void testUpdateProductWithEmpthyName_shouldReturnBadRequest() throws Exception {
        ProductDto dto = new ProductDto(1L, "", 150.0);

        mockMvc.perform(put("/api/v1/product")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.name").value("Name cannot be blank"));
    }
}
