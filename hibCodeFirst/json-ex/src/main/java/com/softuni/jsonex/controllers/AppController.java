package com.softuni.jsonex.controllers;

import com.google.gson.Gson;
import com.softuni.jsonex.dtos.CategorySeedDto;
import com.softuni.jsonex.dtos.ProductAndSellerDto;
import com.softuni.jsonex.dtos.ProductSeedDto;
import com.softuni.jsonex.dtos.UserSeedDto;
import com.softuni.jsonex.services.CategoryService;
import com.softuni.jsonex.services.ProductService;
import com.softuni.jsonex.services.UserService;
import com.softuni.jsonex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.softuni.jsonex.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {
    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileUtil fileUtil;
@Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileUtil fileUtil) {
        this.gson = gson;
    this.categoryService = categoryService;
    this.userService = userService;
    this.productService = productService;
    this.fileUtil = fileUtil;
}

    @Override
    public void run(String... args) throws Exception {
//this.seedCategories();
//this.seedUsers();
//this.seedProducts();
this.writeProductInRange();
    }

    private void writeProductInRange() throws IOException {
        List<ProductAndSellerDto> dtos=this.productService.getAllProductsInRange();
        String json=this.gson.toJson(dtos);
        this.fileUtil.write(json,"src/main/resources/files/outputEx1");
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[]dtos=this.gson.fromJson(new FileReader(PRODUCTS_FILE_PATH),ProductSeedDto[].class);
        this.productService.seedProducts(dtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[]dtos=this.gson.fromJson(new FileReader(USERS_FILE_PATH),UserSeedDto[].class);
        this.userService.seedUsers(dtos);
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto []dtos=this.gson.fromJson(new FileReader(CATEGORIES_FILE_PATH),
                CategorySeedDto[].class);

        this.categoryService.seedCategories(dtos);
    }
}
