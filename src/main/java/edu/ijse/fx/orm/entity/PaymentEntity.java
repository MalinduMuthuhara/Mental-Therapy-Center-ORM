package edu.ijse.fx.orm.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name="payments")

public class PaymentEntity {
    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @OneToOne
    @JoinColumn(name = "session_id")
    private SessionEntity sessionEntity;

    @Column
    private String date;

}
