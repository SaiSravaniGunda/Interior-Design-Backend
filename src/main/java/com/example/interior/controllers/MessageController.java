package com.example.interior.controllers;

import com.example.interior.models.Message;
import com.example.interior.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam String content,
            @RequestParam(required = false) MultipartFile file) throws IOException {
        Message message = messageService.sendMessage(senderId, recipientId, content, file);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/unread/{recipientId}/from/{senderId}")
    public ResponseEntity<Long> getUnreadMessagesFromSender(
            @PathVariable Long recipientId,
            @PathVariable Long senderId) {
        long unreadCount = messageService.getUnreadMessageCountFromSender(recipientId, senderId);
        return ResponseEntity.ok(unreadCount);
    }

    @GetMapping("/unread-messages/{recipientId}/from/{senderId}")
    public ResponseEntity<List<Message>> getUnreadMessages(
            @PathVariable Long recipientId,
            @PathVariable Long senderId) {
        List<Message> messages = messageService.getUnreadMessagesFromSender(recipientId, senderId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/mark-read/{recipientId}/from/{senderId}")
    public ResponseEntity<Void> markMessagesAsRead(
            @PathVariable Long recipientId,
            @PathVariable Long senderId) {
        messageService.markMessagesAsRead(recipientId, senderId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/history/{senderId}/with/{recipientId}")
    public ResponseEntity<List<Message>> getChatHistory(
            @PathVariable Long senderId,
            @PathVariable Long recipientId) {
        List<Message> messages = messageService.getChatHistory(senderId, recipientId);
        return ResponseEntity.ok(messages);
    }

}
