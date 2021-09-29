package com.softuni.jsonex.services;

import com.softuni.jsonex.dtos.CategorySeedDto;
import com.softuni.jsonex.entities.Category;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDto[]categorySeedDtos);
    List<Category> getRandomCategories();
}
