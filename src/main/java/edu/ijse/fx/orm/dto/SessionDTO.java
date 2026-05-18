package edu.ijse.fx.orm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SessionDTO {
    private String sessionId;
    private String therapistId;
    private String patientId;
    private String date;
    private String time;
}
