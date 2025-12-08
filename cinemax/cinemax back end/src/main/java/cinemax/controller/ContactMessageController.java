package cinemax.controller;

import cinemax.model.ContactMessage;
import cinemax.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactMessageController {

    @Autowired
    private ContactMessageService service;

    @GetMapping
    public List<ContactMessage> getAllMessages() {
        return service.getAllMessages();
    }

    @PostMapping
    public ContactMessage sendMessage(@RequestBody ContactMessage message) {
        return service.saveMessage(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        service.deleteMessage(id);
    }
}
