package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @GetMapping("/byCart/{cartId}")
    public List<CartItem> getCartItemsByCart(@PathVariable Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @PostMapping
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) {
        return cartItemRepository.findById(id).map(cartItem -> {
            cartItem.setCart(updatedCartItem.getCart());
            cartItem.setProduct(updatedCartItem.getProduct());
            cartItem.setQuantity(updatedCartItem.getQuantity());
            return cartItemRepository.save(cartItem);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartItemRepository.deleteById(id);
    }
}