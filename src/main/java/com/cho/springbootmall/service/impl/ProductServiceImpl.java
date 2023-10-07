package com.cho.springbootmall.service.impl;

import com.cho.springbootmall.dao.ProductDao;
import com.cho.springbootmall.dto.ProductRequest;
import com.cho.springbootmall.model.Product;
import com.cho.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
}
