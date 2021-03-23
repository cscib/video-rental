package com.cscib.videorental.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="price")
public class Price {

    @Id
    private String id;


    @OneToOne(mappedBy="price")
    private PriceCategory category;

    private BigDecimal amount;

    private String currency;
}

