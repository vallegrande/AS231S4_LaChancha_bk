package pe.edu.vallegrande.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.vallegrande.ventas.model.Reservation;
import pe.edu.vallegrande.ventas.model.ReservationDetail;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {
    List<ReservationDetail> findByReservation(Reservation reservation);
}
