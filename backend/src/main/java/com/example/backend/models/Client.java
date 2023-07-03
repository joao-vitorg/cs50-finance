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
    private BigDecimal balance;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal virtualBalance;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private Set<ClientStock> clientStocks = new LinkedHashSet<>();

    public void transactVirtualBalance(BigDecimal amount) {
        BigDecimal newVirtualBalance = getVirtualBalance().add(amount);
        if (newVirtualBalance.compareTo(BigDecimal.valueOf(0)) < 0) throw new Error("Insufficient funds.");
        setVirtualBalance(newVirtualBalance);
    }

    public void transactBalance(BigDecimal amount) {
        BigDecimal newBalance = getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.valueOf(0)) < 0) throw new Error("Insufficient funds.");
        transactVirtualBalance(amount.negate());
        setBalance(newBalance);
    }
}
