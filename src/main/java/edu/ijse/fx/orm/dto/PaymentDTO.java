package edu.ijse.fx.orm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PaymentDTO {
    private String paymentId;
    private String sessionId;
    private String date;
}
