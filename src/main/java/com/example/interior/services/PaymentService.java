package com.example.interior.services;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final String KEY_ID = "rzp_test_Qi1InUYyVMLZdY";
    private static final String KEY_SECRET = "BeeIkxZiO14cnXeLcgPIFmrq";

//    public String createPaymentOrder(double amount) throws Exception {
//        RazorpayClient razorpay = new RazorpayClient(KEY_ID, KEY_SECRET);
//        System.out.println(amount);
//        JSONObject options = new JSONObject();
//        options.put("amount", (long) amount); // Amount is already in paise
//        options.put("currency", "INR");
//        options.put("receipt", "txn_123456");
//        options.put("payment_capture", 1);
//
//        Order order = razorpay.orders.create(options);
//        return order.get("id");
//    }
    public ResponseEntity<String> createPaymentOrder(double amount) throws Exception {
        RazorpayClient razorpay = new RazorpayClient(KEY_ID, KEY_SECRET);
        System.out.println(amount);

        JSONObject options = new JSONObject();
        options.put("amount", (long) amount); // Amount is already in paise
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        options.put("payment_capture", 1);

        Order order = razorpay.orders.create(options);
        return ResponseEntity.ok(order.toString());
    }

}
