package com.example.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, updatable = false)
    private String symbol;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant modifiedAt;

    @OneToMany(mappedBy = "stock", orphanRemoval = true)
    private Set<ClientStock> clientStocks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock", orphanRemoval = true)
    private Set<Transaction> transactions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock", orphanRemoval = true)
    private Set<StockHistory> histories = new LinkedHashSet<>();
}
