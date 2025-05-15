package com.soft.categoryservice.repository;

import com.soft.categoryservice.entities.Category;
import com.soft.categoryservice.entities.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    public Page<SubCategory> findByNameContains(String name, Pageable pageable);

    public Page<SubCategory> findByCategory(Category category, Pageable pageable);
}
