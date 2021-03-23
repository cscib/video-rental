package com.cscib.videorental.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="surcharge")
public class Surcharge {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="payment_id",foreignKey = @ForeignKey(name = "FK_payment_surcharges"))
    private Payment payment;

    private BigDecimal amount;

    private String currency;

    private BigDecimal surcharge;

    @Column(name="paid_amount")
    private OffsetDateTime paidAmountOn;

    @Column(name="paid_surcharge")
    private OffsetDateTime paidSurchargeOn;

    @OneToOne(mappedBy = "surcharge")
    private Rental rental;

}

