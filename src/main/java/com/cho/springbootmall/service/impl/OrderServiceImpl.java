package com.cho.springbootmall.service.impl;

import com.cho.springbootmall.dao.OrderDao;
import com.cho.springbootmall.dao.ProductDao;
import com.cho.springbootmall.dao.UserDao;
import com.cho.springbootmall.dto.BuyItem;
import com.cho.springbootmall.dto.CreateOrderRequest;
import com.cho.springbootmall.dto.OrderQueryParams;
import com.cho.springbootmall.model.Order;
import com.cho.springbootmall.model.OrderItem;
import com.cho.springbootmall.model.Product;
import com.cho.springbootmall.model.User;
import com.cho.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }

        return orderList;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserByID(userId);

        if (user == null) {
            log.warn("User id {} is not exist", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //check number of product
             if (product == null){
                log.warn("Product {} not exist", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

             } else if (product.getStock() < buyItem.getQuantity()) {
                 log.warn("Product {} number is not enough, remain {} and you want to buy {}",
                         buyItem.getProductId(),product.getStock(), buyItem.getQuantity());
                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
             }

             // deduct number of product
             productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

            //total amount
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            // convert buyItem to orderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }
        //create order
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
