package com.denisar5.perfumehub.service.impl;

import com.denisar5.perfumehub.model.entity.Order;
import com.denisar5.perfumehub.model.entity.Perfume;
import com.denisar5.perfumehub.model.entity.UserEntity;
import com.denisar5.perfumehub.model.enums.OrderStatus;
import com.denisar5.perfumehub.exception.InsufficientStockException;
import com.denisar5.perfumehub.exception.OrderNotFoundException;
import com.denisar5.perfumehub.exception.UnauthorizedActionException;
import com.denisar5.perfumehub.repository.OrderRepository;
import com.denisar5.perfumehub.repository.PerfumeRepository;
import com.denisar5.perfumehub.repository.UserRepository;
import com.denisar5.perfumehub.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PerfumeRepository perfumeRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            PerfumeRepository perfumeRepository,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.perfumeRepository = perfumeRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void completeOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be completed");
        }

        order.setStatus(OrderStatus.COMPLETED);

        orderRepository.save(order);
    }

    @Override
    public void cancelOrderByAdmin(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be cancelled");
        }

        Perfume perfume = order.getPerfume();
        perfume.setStockQuantity(perfume.getStockQuantity() + order.getQuantity());

        order.setStatus(OrderStatus.CANCELLED);

        perfumeRepository.save(perfume);
        orderRepository.save(order);
    }

    @Override
    public void createOrder(UUID perfumeId, UUID userId, Integer quantity) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new RuntimeException("Perfume not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (quantity == null || quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        if (perfume.getStockQuantity() < quantity) {
            throw new InsufficientStockException("Not enough perfume in stock");
        }

        BigDecimal totalPrice = perfume.getPrice()
                .multiply(BigDecimal.valueOf(quantity));

        Order order = Order.builder()
                .user(user)
                .perfume(perfume)
                .quantity(quantity)
                .totalPrice(totalPrice)
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        perfume.setStockQuantity(perfume.getStockQuantity() - quantity);

        perfumeRepository.save(perfume);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersForUser(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findAllByUser(user);
    }

    @Override
    public void cancelOrder(UUID orderId, UUID userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        if (!order.getUser().getId().equals(userId)) {
            throw new UnauthorizedActionException("You cannot cancel another user's order");
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be cancelled");
        }

        Perfume perfume = order.getPerfume();
        perfume.setStockQuantity(perfume.getStockQuantity() + order.getQuantity());

        order.setStatus(OrderStatus.CANCELLED);

        perfumeRepository.save(perfume);
        orderRepository.save(order);
    }
}
