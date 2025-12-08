package cinemax.service;

import cinemax.model.Movie;
import cinemax.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Récupérer tous les films
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Récupérer un film par ID
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    // Créer un nouveau film
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Mettre à jour un film
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Supprimer un film
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
