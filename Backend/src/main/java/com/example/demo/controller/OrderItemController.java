package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderItemRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @GetMapping("/byOrder/{orderId}")
    public List<OrderItem> getOrderItemsByOrder(@PathVariable Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@PathVariable Long id, @RequestBody OrderItem updatedOrderItem) {
        return orderItemRepository.findById(id).map(orderItem -> {
            orderItem.setOrder(updatedOrderItem.getOrder());
            orderItem.setProduct(updatedOrderItem.getProduct());
            orderItem.setQuantity(updatedOrderItem.getQuantity());
            orderItem.setUnitPrice(updatedOrderItem.getUnitPrice());
            return orderItemRepository.save(orderItem);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemRepository.deleteById(id);
    }
}