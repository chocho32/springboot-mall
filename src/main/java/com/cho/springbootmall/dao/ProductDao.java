package com.cho.springbootmall.dao;

import com.cho.springbootmall.dto.ProductRequest;
import com.cho.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
