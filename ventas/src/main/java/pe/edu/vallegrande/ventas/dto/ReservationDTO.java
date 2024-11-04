package pe.edu.vallegrande.ventas.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ReservationDTO {
    private Long idUser;
    private Timestamp registrationDate;
    private String phone;
    private List<ReservationDetailDTO> reservationDetails;
}
