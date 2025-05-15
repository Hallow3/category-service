package com.soft.categoryservice.controller;

import com.soft.categoryservice.entities.Category;
import com.soft.categoryservice.entities.SubCategory;
import com.soft.categoryservice.repository.CategoryRepository;
import com.soft.categoryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<Page<Category>> getAllCategoriesPage(@PathVariable("page") int page){
        return new ResponseEntity<>(categoryService.getAllCategoriesPage(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Page<Category>> getCategoryByName(@PathVariable("name") String name, @RequestParam(name = "page") int pageNo){
        return new ResponseEntity<>(categoryService.getCategoryByNameContains(name, pageNo), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestParam("image")MultipartFile image){
        return new ResponseEntity<>(categoryService.addCategory(category, image), HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id){
        categoryService.deleteCategory(id);
        return "successfully deleted";
    }

    @GetMapping("/test")
    public List<Category> displayMessage(){
        return categoryRepository.findAll();
    }

    @GetMapping("/subcategory")
    public ResponseEntity<List<SubCategory>> getAllSubCategory(@RequestParam(name = "id") int idCategory){
        return new ResponseEntity<>(categoryService.getCategoryById(idCategory).getSubCategories(), HttpStatus.OK);
    }

    @GetMapping("/sub")
    public ResponseEntity<Category> getBySubCategory(@RequestParam(name = "id") int idSubCategory){
        return new ResponseEntity<>(categoryService.getCategoryBySubCategory(idSubCategory), HttpStatus.OK);
    }

}
