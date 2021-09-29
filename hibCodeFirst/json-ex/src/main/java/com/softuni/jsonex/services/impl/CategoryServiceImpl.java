package com.softuni.jsonex.services.impl;

import com.softuni.jsonex.dtos.CategorySeedDto;
import com.softuni.jsonex.entities.Category;
import com.softuni.jsonex.repositories.CategoryRepository;
import com.softuni.jsonex.services.CategoryService;
import com.softuni.jsonex.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
if (this.categoryRepository.count()!=0){
    return;
}
        Arrays.stream(categorySeedDtos)
                .forEach(categorySeedDto -> {
                    if (this.validationUtil.isValid(categorySeedDto)){
                        Category category=this.modelMapper.map(categorySeedDto, Category.class);
                        this.categoryRepository.saveAndFlush(category);
                    }else {
                        this.validationUtil.getViolations(categorySeedDto)
                                .stream().map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<Category> getRandomCategories() {
        Random random=new Random();
        List<Category> resultList=new ArrayList<>();
        int randomCounter=random.nextInt(3)+1;
        for (int i = 0; i <randomCounter ; i++) {
long randomId=random.nextInt((int) this.categoryRepository.count())+1;
resultList.add(this.categoryRepository.getOne(randomId));
        }
    return resultList;
    }
}
