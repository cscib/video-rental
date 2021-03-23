package com.cscib.videorental.data.model;

import com.univocity.parsers.annotations.Parsed;
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
@Table(name="price_category")
public class PriceCategory {

    @Id
    @Parsed
    private Integer id;

    @Parsed
    private BigDecimal amount;

    @Parsed
    private String currency;

    // basic, premium
    @Parsed
    private String type;



}

