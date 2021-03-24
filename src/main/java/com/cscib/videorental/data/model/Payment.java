package com.cscib.videorental.data.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue
    private Integer ids;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="client_id",foreignKey = @ForeignKey(name = "FK_client_payments"))
    private Client client;

    private BigDecimal amount;

    private String currency;

    @Column(name="paid_amount")
    private OffsetDateTime paidAmountOn;

    @OneToMany(mappedBy="payment")
    private List<Rental> rentals;

    @OneToMany(mappedBy="payment")
    private List<Surcharge> surcharges;

}

