package com.example.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance = BigDecimal.valueOf(1000);

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal VirtualBalance = BigDecimal.valueOf(0);

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private Set<UserStock> userStocks = new LinkedHashSet<>();
}
