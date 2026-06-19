package com.denisar5.perfumehub.controller;


import com.denisar5.perfumehub.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/{perfumeId}")
    public String createOrder(@PathVariable UUID perfumeId,
                              @RequestParam Integer quantity,
                              HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");

        if (userId == null) {
            return "redirect:/login";
        }

        orderService.createOrder(perfumeId, userId, quantity);

        return "redirect:/orders/my";
    }

    @GetMapping("/my")
    public String myOrders(HttpSession session, Model model) {
        UUID userId = (UUID) session.getAttribute("user_id");

        if (userId == null) {
            return "redirect:/login";
        }

        model.addAttribute("orders", orderService.getOrdersForUser(userId));

        return "my-orders";
    }

    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable UUID orderId,
                              HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");

        if (userId == null) {
            return "redirect:/login";
        }

        orderService.cancelOrder(orderId, userId);

        return "redirect:/orders/my";
    }
}