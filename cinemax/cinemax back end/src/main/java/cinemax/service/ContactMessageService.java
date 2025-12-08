package cinemax.service;

import cinemax.model.ContactMessage;
import cinemax.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository repository;

    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }

    public ContactMessage saveMessage(ContactMessage message) {
        return repository.save(message);
    }

    public void deleteMessage(Long id) {
        repository.deleteById(id);
    }
}
