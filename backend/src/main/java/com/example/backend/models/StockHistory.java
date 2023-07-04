package com.example.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false)
    private Stock stock;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdAt;

    public StockHistory(Stock stock, BigDecimal price) {
        this.stock = stock;
        this.price = price;
    }
}
