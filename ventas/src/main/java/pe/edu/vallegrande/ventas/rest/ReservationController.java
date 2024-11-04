package pe.edu.vallegrande.ventas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.ventas.dto.ReservationDTO;
import pe.edu.vallegrande.ventas.dto.UpdateReservationDTO;
import pe.edu.vallegrande.ventas.model.Reservation;
import pe.edu.vallegrande.ventas.model.ReservationDetail;
import pe.edu.vallegrande.ventas.service.ReservationService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Crear una nueva reserva
    @PostMapping("/register")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setIdUser(reservationDTO.getIdUser());
        reservation.setRegistrationDate(reservationDTO.getRegistrationDate());
        reservation.setPhone(reservationDTO.getPhone());

        // Convierte y añade detalles de reserva si es necesario
        // Por ejemplo, puedes añadir un bucle aquí para añadir los detalles
        reservation.setReservationDetails(reservationDTO.getReservationDetails().stream()
                .map(detailDTO -> {
                    ReservationDetail detail = new ReservationDetail();
                    detail.setReservation(reservation); // Para establecer la relación
                    detail.setReservationDate(detailDTO.getReservationDate());
                    detail.setNumberPeople(detailDTO.getNumberPeople());
                    detail.setDescription(detailDTO.getDescription());
                    detail.setStatus(detailDTO.getStatus());
                    return detail;
                }).toList());

        Reservation savedReservation = reservationService.save(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.findById(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservation);
    }

    // Obtener detalles de una reserva
    @GetMapping("/{id}/details")
    public ResponseEntity<List<ReservationDetail>> getReservationDetails(@PathVariable Long id) {
        List<ReservationDetail> details = reservationService.getReservationDetails(id);
        if (details.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(details);
    }

    // Listar todas las reservas
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/estado")
    public ResponseEntity<ReservationDetail> updateStatus(@RequestBody Map<String, Object> request) {
        Long id = Long.valueOf(request.get("id").toString());
        String newStatus = (String) request.get("status");
        
        ReservationDetail updatedDetail = reservationService.updateStatus(id, newStatus);
        return ResponseEntity.ok(updatedDetail);
    }

    @PutMapping("/{id}")
public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody UpdateReservationDTO updateReservationDTO) {
    Reservation updatedReservation = reservationService.updateReservation(id, updateReservationDTO);
    return ResponseEntity.ok(updatedReservation);
}


}
