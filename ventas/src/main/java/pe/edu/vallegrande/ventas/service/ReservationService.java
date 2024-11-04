package pe.edu.vallegrande.ventas.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.ventas.dto.UpdateReservationDTO;
import pe.edu.vallegrande.ventas.model.Reservation;
import pe.edu.vallegrande.ventas.model.ReservationDetail;
import pe.edu.vallegrande.ventas.repository.ReservationDetailRepository;
import pe.edu.vallegrande.ventas.repository.ReservationRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservación no encontrada"));
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<ReservationDetail> getReservationDetails(Long reservationId) {
        Reservation reservation = findById(reservationId);
        return reservationDetailRepository.findByReservation(reservation);
    }

    public ReservationDetail updateStatus(Long id, String newStatus) {
    ReservationDetail reservationDetail = reservationDetailRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("ReservationDetail not found"));

    reservationDetail.setStatus(newStatus); 
    return reservationDetailRepository.save(reservationDetail); 
}
    

public Reservation updateReservation(Long id, UpdateReservationDTO updateReservationDTO) {
    Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Reservation not found"));

    reservation.setIdUser(updateReservationDTO.getIdUser());
    reservation.setRegistrationDate(updateReservationDTO.getRegistrationDate());
    reservation.setPhone(updateReservationDTO.getPhone());

    List<ReservationDetail> updatedDetails = reservationDetailRepository.findByReservation(reservation);
    for (UpdateReservationDTO.ReservationDetailDTO detailDTO : updateReservationDTO.getReservationDetails()) {
        ReservationDetail detail = updatedDetails.stream()
            .filter(d -> d.getIdReservationDetail().equals(detailDTO.getIdReservationDetail()))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("ReservationDetail not found"));

        detail.setReservationDate(detailDTO.getReservationDate());
        detail.setNumberPeople(detailDTO.getNumberPeople());
        detail.setDescription(detailDTO.getDescription());
        detail.setStatus(detailDTO.getStatus());
    }

    reservationRepository.save(reservation);
    reservationDetailRepository.saveAll(updatedDetails);

    return reservation;
}
    
}
