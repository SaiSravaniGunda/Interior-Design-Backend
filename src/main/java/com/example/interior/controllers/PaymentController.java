package com.example.interior.controllers;

import com.example.interior.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public  ResponseEntity<String> createPayment(@RequestParam double amount) throws Exception {
        return paymentService.createPaymentOrder(amount);
    }
}
