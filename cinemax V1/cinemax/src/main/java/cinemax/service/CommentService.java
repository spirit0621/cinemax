package cinemax.service;

import cinemax.model.Comment;
import cinemax.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // Récupérer tous les commentaires
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Récupérer un commentaire par ID
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Créer un nouveau commentaire
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Mettre à jour un commentaire
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Supprimer un commentaire
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    // Récupérer les commentaires d'un film
    public List<Comment> getCommentsByMovieId(Long movieId) {
        return commentRepository.findByMovieId(movieId);
    }
}
