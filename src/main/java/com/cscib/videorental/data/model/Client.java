package com.cscib.videorental.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="client")
public class Client {

    @Id
    private String id;

    @OneToMany(mappedBy="client")
    private List<Rental> rentals;

    @OneToMany(mappedBy="client")
    private List<Payment> payments;

    @Column(name = "bonus_points")
    private int bonusPoints;
}

