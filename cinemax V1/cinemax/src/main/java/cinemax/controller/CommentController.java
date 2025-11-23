package cinemax.controller;

import cinemax.model.Comment;
import cinemax.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // GET tous les commentaires
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // GET un commentaire par ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET commentaires d'un film
    @GetMapping("/movie/{movieId}")
    public List<Comment> getCommentsByMovieId(@PathVariable Long movieId) {
        return commentService.getCommentsByMovieId(movieId);
    }

    // POST créer un nouveau commentaire
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    // PUT mettre à jour un commentaire
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> existingComment = commentService.getCommentById(id);
        if (existingComment.isPresent()) {
            comment.setId(id);
            return ResponseEntity.ok(commentService.updateComment(comment));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE supprimer un commentaire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
