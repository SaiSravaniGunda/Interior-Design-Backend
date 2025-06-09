package com.example.interior.services;

import com.example.interior.models.Message;
import com.example.interior.models.User;
import com.example.interior.repositories.MessageRepository;
import com.example.interior.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.nio.file.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    


    public Message sendMessage(Long senderId, Long recipientId, String content, MultipartFile file) throws IOException {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        String fileUrl = null;
        if (file != null && !file.isEmpty()) {
            // Get absolute path for the uploads directory
            String uploadDir = System.getProperty("user.dir") + "/uploads/";

            // Create the directory if it doesn't exist
            Files.createDirectories(Paths.get(uploadDir));

            // Define file path
            String filePath = uploadDir + file.getOriginalFilename();
            System.out.println("File saved at: " + filePath);
            file.transferTo(new File(filePath));


            fileUrl = "/uploads/" + file.getOriginalFilename();
        }

        Message message = new Message(sender, recipient, content, fileUrl);
        return messageRepository.save(message);
    }


    public long getUnreadMessageCountFromSender(Long recipientId, Long senderId) {
        return messageRepository.countUnreadMessagesFromSender(recipientId, senderId);
    }

    public List<Message> getUnreadMessagesFromSender(Long recipientId, Long senderId) {
        return messageRepository.findUnreadMessagesFromSender(recipientId, senderId);
    }

    public void markMessagesAsRead(Long recipientId, Long senderId) {
        List<Message> unreadMessages = messageRepository.findUnreadMessagesFromSender(recipientId, senderId);
        unreadMessages.forEach(message -> message.setRead(true));
        messageRepository.saveAll(unreadMessages);
    }
    
    public List<Message> getChatHistory(Long senderId, Long recipientId) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found"));
        User recipient = userRepository.findById(recipientId).orElseThrow(() -> new RuntimeException("Recipient not found"));

        return messageRepository.findAllBySenderAndRecipient(sender, recipient);
    }

}
