package com.cho.springbootmall.service;

import com.cho.springbootmall.dto.CreateOrderRequest;
import com.cho.springbootmall.dto.OrderQueryParams;
import com.cho.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
