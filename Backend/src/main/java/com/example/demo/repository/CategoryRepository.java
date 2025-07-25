package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

	@SuppressWarnings("unchecked")
	Category save(com.example.demo.model.Category category);
}
