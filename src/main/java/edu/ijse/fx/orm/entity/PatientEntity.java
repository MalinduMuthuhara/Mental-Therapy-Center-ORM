package edu.ijse.fx.orm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "patients")

public class PatientEntity {
    @Id
    @Column(name = "patient_id")
    private String patientId;

    @Column
    private String patientName;

    @Column
    private String gender;

    @Column
    private String patientPhone;
}
