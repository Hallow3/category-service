package com.soft.categoryservice.repository;

import com.soft.categoryservice.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Page<Category> findByNameContains(String name, Pageable pageable);
}
