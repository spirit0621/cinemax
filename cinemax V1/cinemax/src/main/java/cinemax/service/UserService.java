package cinemax.service;

import cinemax.model.User;
import cinemax.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Créer un nouvel utilisateur
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Mettre à jour un utilisateur
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Trouver un utilisateur par nom d'utilisateur
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Vérifier les identifiants de connexion
    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}
