package com.soft.categoryservice.serviceImplement;

import com.soft.categoryservice.entities.Category;
import com.soft.categoryservice.entities.SubCategory;
import com.soft.categoryservice.repository.CategoryRepository;
import com.soft.categoryservice.repository.SubCategoryRepository;
import com.soft.categoryservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImplement implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public Category addCategory(Category category, MultipartFile image) {
        try {
            if(image.getSize() > 5000000 || image.getSize()< 0){
                log.info("your image size is out of bounds");
                return null;
            }
            //on extrait le nom et on place limage dans le serveur
            String imageName = StringUtils.cleanPath(image.getOriginalFilename());
            if(!imageName.isEmpty() && !imageName.trim().equals(" ")){
                System.out.println("ajout de: "+imageName);
                File fichier = new File("C:\\Users\\Halloween\\Documents\\workspace-spring-tool-suite-4-4.15.3.RELEASE\\Digest\\digest-front\\src\\assets\\images\\categories\\"+imageName);
                fichier.createNewFile();
                FileOutputStream fout = new FileOutputStream(fichier);
                fout.write(image.getBytes());
                fout.close();
                category.setPicture(imageName);
                return categoryRepository.save(category);
                //
            }else{
                log.info("error while trying to get image name");
                return null;
            }
        }catch (Exception e){
            log.info("an error has been found while trying to add category");
            return category;
        }
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        Category category1 = categoryRepository.findById(category.getId()).get();
        category1.setName(category.getName());
        category1.setPicture(category.getPicture());
        category1.setDescription(category.getDescription());
        return categoryRepository.save(category1);
    }

    @Override
    public Page<Category> getCategoryByNameContains(String name, int pageNo) {

        Pageable pageable = PageRequest.of(pageNo, 12);
        return categoryRepository.findByNameContains(name, pageable);
    }

    @Override
    public Page<Category> getAllCategoriesPage(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategoryBySubCategory(int subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).get();
        return subCategory.getCategory();
    }
}
