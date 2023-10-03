package com.cho.springbootmall.dao;

import com.cho.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
