package edu.ijse.fx.orm.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "sessions")

public class SessionEntity {
    @Id
    @Column(name = "session_id")
    private String sessionId;

    @ManyToOne//(Session godai therapist1)
    @JoinColumn(name = "therapist_id")
    private TherapistEntity therapistEntity;

    @ManyToOne//(sessions godakata patients la ekkenai
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patientEntity;

    @Column
    private String date;

    @Column
    private String time;


}
