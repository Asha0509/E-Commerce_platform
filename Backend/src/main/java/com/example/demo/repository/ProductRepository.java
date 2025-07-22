package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<com.example.demo.model.Product, Long> {
    List<com.example.demo.model.Product> findByTenantId(Long tenantId);
    List<com.example.demo.model.Product> findByCategoryId(Long categoryId);
}
