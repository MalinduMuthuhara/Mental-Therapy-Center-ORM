package edu.ijse.fx.orm.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="therapists")

public class TherapistEntity {
    @Id
    @Column(name = "therapist_id")
    private String therapistId;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private ProgramEntity programEntity;

    @Column
    private String therapistName;

    @Column
    private String therapistPhone;





}
