package cinemax.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long movieId;
    private String title;
    private String genre;
    private String showtime;
    private Double price;
    private String customerName;
    private String customerEmail;
    private Integer seatsBooked;
    private String createdAt;
    private Long userId;
    private String userRole;
}
