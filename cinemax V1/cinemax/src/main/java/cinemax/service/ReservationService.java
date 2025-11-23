package cinemax.service;

import cinemax.model.Reservation;
import cinemax.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Récupérer toutes les réservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Récupérer une réservation par ID
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    // Créer une nouvelle réservation
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Mettre à jour une réservation
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Supprimer une réservation
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    // Récupérer les réservations d'un utilisateur
    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}
