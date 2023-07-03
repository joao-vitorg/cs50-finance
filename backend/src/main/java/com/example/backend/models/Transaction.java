package com.example.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Stock stock;

    @Column(nullable = false)
    private Integer shares;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal total;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdAt;
}
