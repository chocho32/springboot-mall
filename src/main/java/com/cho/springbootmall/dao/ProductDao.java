package com.cho.springbootmall.dao;

import com.cho.springbootmall.constant.ProductCategory;
import com.cho.springbootmall.dto.ProductRequest;
import com.cho.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
