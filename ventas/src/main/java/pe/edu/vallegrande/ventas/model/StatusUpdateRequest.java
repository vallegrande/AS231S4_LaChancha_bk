package pe.edu.vallegrande.ventas.model;

import lombok.Data;

@Data
public class StatusUpdateRequest {
    private Long id;
    private String status;
}