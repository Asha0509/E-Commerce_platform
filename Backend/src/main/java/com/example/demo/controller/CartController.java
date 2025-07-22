package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @GetMapping("/byUser/{userId}")
    public Cart getCartByUser(@PathVariable Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartRepository.save(cart);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        return cartRepository.findById(id).map(cart -> {
            cart.setUser(updatedCart.getUser());
            cart.setCartItems(updatedCart.getCartItems());
            return cartRepository.save(cart);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartRepository.deleteById(id);
    }
}