package com.softuni.jsonex.services;

import com.softuni.jsonex.dtos.ProductAndSellerDto;
import com.softuni.jsonex.dtos.ProductSeedDto;

import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[]productSeedDtos);
    List<ProductAndSellerDto> getAllProductsInRange();
}
