package com.cscib.videorental.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="rental")
public class Rental {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="movie_id",foreignKey = @ForeignKey(name = "FK_movie_rentals"))
    private Movie movie;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="client_id",foreignKey = @ForeignKey(name = "FK_client_rentals"))
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="payment_id",foreignKey = @ForeignKey(name = "FK_payment_rentals"))
    private Payment payment;

    private OffsetDateTime rentedOn;

    private OffsetDateTime expectedReturnOn;

    private OffsetDateTime returnedOn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "surcharge_id", referencedColumnName = "id")
    private Surcharge surcharge;

}

