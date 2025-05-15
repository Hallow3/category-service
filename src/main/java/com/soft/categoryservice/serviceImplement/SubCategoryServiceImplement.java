package com.soft.categoryservice.serviceImplement;

import com.soft.categoryservice.entities.Category;
import com.soft.categoryservice.entities.SubCategory;
import com.soft.categoryservice.repository.CategoryRepository;
import com.soft.categoryservice.repository.SubCategoryRepository;
import com.soft.categoryservice.service.SubCategoryService;
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
public class SubCategoryServiceImplement implements SubCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategory addSubCategory(SubCategory subcategory, MultipartFile image) {
        try {
            if(image.getSize() > 5000000 || image.getSize()< 0){
                log.info("your image size is out of bounds");
                return null;
            }
            //on extrait le nom et on place limage dans le serveur
            String imageName = StringUtils.cleanPath(image.getOriginalFilename());
            if(!imageName.isEmpty() && !imageName.trim().equals(" ")){
                System.out.println("ajout de: "+imageName);
                File fichier = new File("src/main/resources/static/images/subcategories/"+imageName);
                fichier.createNewFile();
                FileOutputStream fout = new FileOutputStream(fichier);
                fout.write(image.getBytes());
                fout.close();
                subcategory.setPicture(imageName);
                return subCategoryRepository.save(subcategory);
                //
            }else{
                log.info("error while trying to get image name");
                return null;
            }
        }catch (Exception e){
            log.info("an error has been found while trying to add category");
            return null;
        }
    }

    @Override
    public SubCategory getSubCategoryById(int id) {
        return subCategoryRepository.findById(id).get();
    }

    @Override
    public void deleteSubCategory(int id) {
        SubCategory subCategory = subCategoryRepository.findById(id).get();
        removeImage(subCategory.getPicture());
        subCategoryRepository.deleteById(id);
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory updateSubCategory(SubCategory subcategory, MultipartFile image) {
        try {
            SubCategory subCategoryToUpdate = subCategoryRepository.findById(subcategory.getId()).get();
            subCategoryToUpdate.setName(subcategory.getName());
            subCategoryToUpdate.setDescription(subcategory.getDescription());
            if(image != null){
                removeImage(subcategory.getPicture());
                if(image.getSize() > 5000000 || image.getSize()< 0){
                    log.info("your image size is out of bounds");
                    return null;
                }
                //on extrait le nom et on place limage dans le serveur
                String imageName = StringUtils.cleanPath(image.getOriginalFilename());
                if(!imageName.isEmpty() && !imageName.trim().equals(" ")){
                    System.out.println("ajout de: "+imageName);
                    File fichier = new File("src/main/resources/static/images/subcategories/"+imageName);
                    fichier.createNewFile();
                    FileOutputStream fout = new FileOutputStream(fichier);
                    fout.write(image.getBytes());
                    fout.close();
                    subcategory.setPicture(imageName);
            }
                return subCategoryRepository.save(subcategory);
                //
            }else{
                return subCategoryRepository.save(subcategory);
            }
        }catch (Exception e){
            log.info("an error has been found while trying to add category");
            return null;
        }
    }

    @Override
    public Page<SubCategory> getByCategory(int categoryId, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,12);
        Category category = categoryRepository.findById(categoryId).get();
        return subCategoryRepository.findByCategory(category,pageable);
    }

    @Override
    public Page<SubCategory> getByNameContains(String name, int pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber, 12);
        return subCategoryRepository.findByNameContains(name, pageable);
    }

    private void removeImage(String picture) {
        File fichier = new File("C:\\Users\\Halloween\\Documents\\workspace-spring-tool-suite-4-4.15.3.RELEASE\\Digest\\digest-front\\src\\assets\\images\\subcategories\\"+picture);
        fichier.deleteOnExit();
    }
}
