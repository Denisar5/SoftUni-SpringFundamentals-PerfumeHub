package com.denisar5.perfumehub.service;


import com.denisar5.perfumehub.model.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> getAllOrders();

    void completeOrder(UUID orderId);

    void cancelOrderByAdmin(UUID orderId);

    void createOrder(UUID perfumeId, UUID userId, Integer quantity);

    List<Order> getOrdersForUser(UUID userId);

    void cancelOrder(UUID orderId, UUID userId);
}
