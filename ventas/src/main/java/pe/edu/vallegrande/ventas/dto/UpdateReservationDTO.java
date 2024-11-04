package pe.edu.vallegrande.ventas.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class UpdateReservationDTO {
    private Long idUser;
    private Timestamp registrationDate;
    private String phone;
    private List<ReservationDetailDTO> reservationDetails;

    @Data
    public static class ReservationDetailDTO {
        private Long idReservationDetail;
        private Timestamp reservationDate;
        private int numberPeople;
        private String description;
        private String status; 
    }
}
