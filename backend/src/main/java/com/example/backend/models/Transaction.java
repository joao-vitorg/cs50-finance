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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Stock stock;

    @Column(nullable = false, updatable = false)
    private Integer shares;

    @Column(nullable = false, updatable = false, precision = 6, scale = 2)
    private BigDecimal total;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
