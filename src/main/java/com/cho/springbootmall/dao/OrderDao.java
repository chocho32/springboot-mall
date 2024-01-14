package com.cho.springbootmall.dao;

import com.cho.springbootmall.dto.OrderQueryParams;
import com.cho.springbootmall.model.Order;
import com.cho.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer total);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
