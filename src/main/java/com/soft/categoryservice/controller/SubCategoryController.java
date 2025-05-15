package com.soft.categoryservice.controller;

import com.soft.categoryservice.entities.SubCategory;
import com.soft.categoryservice.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/category/subcategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/all")
    public ResponseEntity<List<SubCategory>> getAllSubCategories(){
        return new ResponseEntity<>(subCategoryService.getAllSubCategories(), HttpStatus.OK);
    }

    @GetMapping("/category/{page}")
    public ResponseEntity<Page<SubCategory>> getSubCategoryByCategory(@RequestParam(name = "id") int idCategory, @PathVariable("page") int page){
        return new ResponseEntity<>(subCategoryService.getByCategory(idCategory,page), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    SubCategory findSubCategoryById(@PathVariable(name = "id") int subCategoryId){
        return subCategoryService.getSubCategoryById(subCategoryId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory, @RequestParam("image") MultipartFile image){
        return new ResponseEntity<>(subCategoryService.addSubCategory(subCategory, image), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SubCategory> updateSubCategory(@RequestBody SubCategory subCategory, @RequestParam("image") MultipartFile image){
        return new ResponseEntity<>(subCategoryService.updateSubCategory(subCategory, image), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id){
        return "successfully deleted";
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Page<SubCategory>> getCategoryByName(@PathVariable("name") String name, @RequestParam(name = "page") int pageNo){
        return new ResponseEntity<>(subCategoryService.getByNameContains(name, pageNo), HttpStatus.OK);
    }

}
