package com.soft.categoryservice.service;

import com.soft.categoryservice.entities.Category;
import com.soft.categoryservice.entities.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubCategoryService {


    public SubCategory addSubCategory(SubCategory subcategory, MultipartFile file);

    SubCategory getSubCategoryById(int id);

    public void deleteSubCategory(int id);

    public List<SubCategory> getAllSubCategories();

    public SubCategory updateSubCategory(SubCategory subcategory, MultipartFile image);

    public Page<SubCategory> getByCategory(int categoryId, int pageNumber);

    public Page<SubCategory> getByNameContains(String name, int pageNumber);
}
