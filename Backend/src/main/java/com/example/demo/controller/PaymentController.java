package com.example.demo.controller;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> data) {
        Stripe.apiKey = stripeSecretKey;

        double amount = (double) data.get("amount");

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount((long) (amount * 100)) // in paise or cents
            .setCurrency("inr")
            .build();

        try {
            PaymentIntent intent = PaymentIntent.create(params);
            Map<String, String> responseData = new HashMap<>();
            responseData.put("clientSecret", intent.getClientSecret());
            return responseData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
