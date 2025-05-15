package com.soft.categoryservice.service;

import com.soft.categoryservice.entities.Category;
import com.soft.categoryservice.entities.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

public interface CategoryService {

    public Category addCategory(Category category, MultipartFile file);

    Category getCategoryById(int id);

    public void deleteCategory(int id);

    public List<Category> getAllCategories();

    public Category updateCategory(Category category);

    public Page<Category> getCategoryByNameContains(String name, int pageNumber);

    public Page<Category> getAllCategoriesPage(int page);

    public Category getCategoryBySubCategory(int subCategoryId);
}
