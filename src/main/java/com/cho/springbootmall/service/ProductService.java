package com.cho.springbootmall.service;

import com.cho.springbootmall.dto.ProductRequest;
import com.cho.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
