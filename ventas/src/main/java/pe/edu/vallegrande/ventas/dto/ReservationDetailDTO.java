package pe.edu.vallegrande.ventas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetailDTO {
    private Long idReservationDetail;
    private Long idReservation;
    private Timestamp reservationDate;
    private int numberPeople;
    private String description;
    private String status;
}
