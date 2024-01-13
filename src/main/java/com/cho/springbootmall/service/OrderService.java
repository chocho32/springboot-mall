package com.cho.springbootmall.service;

import com.cho.springbootmall.dto.CreateOrderRequest;
import com.cho.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
