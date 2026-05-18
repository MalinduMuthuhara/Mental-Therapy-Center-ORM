package edu.ijse.fx.orm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="programs")

public class ProgramEntity {
    @Id
    @Column(name = "program_id")
    private String programId;

    @Column
    private String programName;

}
