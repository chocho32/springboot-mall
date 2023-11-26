package com.cho.springbootmall.service;

import com.cho.springbootmall.constant.ProductCategory;
import com.cho.springbootmall.dto.ProductRequest;
import com.cho.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
