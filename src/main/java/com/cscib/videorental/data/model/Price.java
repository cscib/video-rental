package com.cscib.videorental.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Price implements Persistable<Integer> {

    @Id
    private Integer product;

    private BigDecimal amount;

    private String currency;

    @Transient
    private boolean isNew = false;

    @Override
    public Integer getId() {
        return product;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}

