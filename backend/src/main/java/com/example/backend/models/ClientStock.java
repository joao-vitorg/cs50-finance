package com.example.backend.models;

import com.example.backend.infra.exceptions.ClientDontHaveStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ClientStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public ClientStock(Client client, Stock stock, Integer shares) {
        this.client = client;
        this.stock = stock;
        this.shares = shares;
    }

    public BigDecimal getTotal() {
        return getStock().getPrice().multiply(BigDecimal.valueOf(getShares()));
    }

    public void transactShares(Integer shares) {
        int newShares = this.shares + shares;
        if (newShares < 0) throw new ClientDontHaveStockException();
        setShares(newShares);
    }
}
