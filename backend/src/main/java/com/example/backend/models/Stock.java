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

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant modifiedAt;

    @OneToMany(mappedBy = "stock", orphanRemoval = true)
    private Set<UserStock> userStocks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock", orphanRemoval = true)
    private Set<StockHistory> stockHistories = new LinkedHashSet<>();
}
