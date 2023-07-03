package com.example.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ClientStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Client client;

    @NaturalId
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Stock stock;

    @Column(nullable = false)
    private Integer shares = 0;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal total = BigDecimal.valueOf(0);

    public void transactShares(Integer shares) {
        int newShares = this.shares + shares;
        if (newShares < 0) throw new Error("Cliente não possui a quantidade necessária de Stocks");
        setShares(newShares);
    }

    @PostPersist
    @PostUpdate
    public void trigger() {
        BigDecimal price = getStock().getPrice();
        setTotal(price.multiply(BigDecimal.valueOf(getShares())));
    }
}
