package edu.ijse.fx.orm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PatientDTO {
    private String patientId;
    private String patientName;
    private String gender;
    private String patientPhone;
}
