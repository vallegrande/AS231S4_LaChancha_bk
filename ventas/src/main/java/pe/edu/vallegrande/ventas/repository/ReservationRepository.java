package pe.edu.vallegrande.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.vallegrande.ventas.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Puedes agregar métodos personalizados si los necesitas
}
