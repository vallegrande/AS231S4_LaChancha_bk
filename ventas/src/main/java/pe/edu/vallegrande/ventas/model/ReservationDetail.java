package pe.edu.vallegrande.ventas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "reservation_details")

public class ReservationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation_details")
    private Long idReservationDetail;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    @JsonIgnore 
    private Reservation reservation;

    @Column(name = "reservation_date", nullable = false)
    private Timestamp reservationDate;

    @Column(name = "number_people", nullable = false)
    private int numberPeople;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "status", nullable = false)
    private String status;
}
