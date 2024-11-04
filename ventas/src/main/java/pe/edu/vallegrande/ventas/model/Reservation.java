package pe.edu.vallegrande.ventas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;

    @Column(name = "phone", nullable = false, length = 9)
    private String phone;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReservationDetail> reservationDetails;
}
