package com.denisar5.perfumehub.controller;

import com.denisar5.perfumehub.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String allOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());

        return "admin-orders";
    }

    @PostMapping("/complete/{orderId}")
    public String completeOrder(@PathVariable UUID orderId) {
        orderService.completeOrder(orderId);

        return "redirect:/admin/orders";
    }

    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable UUID orderId) {
        orderService.cancelOrderByAdmin(orderId);

        return "redirect:/admin/orders";
    }
}
