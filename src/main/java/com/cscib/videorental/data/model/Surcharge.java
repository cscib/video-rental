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
@Table(name="surcharge")
public class Surcharge {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="payment_id",foreignKey = @ForeignKey(name = "FK_payment_surcharges"))
    private Payment payment;

    private String currency;

    private BigDecimal amount;

    @Column(name="paid_surcharge")
    private OffsetDateTime paidSurchargeOn;

    @OneToOne(mappedBy = "surcharge")
    private Rental rental;

}

