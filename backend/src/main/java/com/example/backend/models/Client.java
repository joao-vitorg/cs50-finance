package com.example.backend.models;

import com.example.backend.infra.exceptions.InsufficientFundsException;
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
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance = BigDecimal.valueOf(10000);

    @OneToMany(mappedBy = "client")
    private Set<ClientStock> clientStocks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Transaction> transactions = new LinkedHashSet<>();

    public void transactBalance(BigDecimal amount) {
        BigDecimal newBalance = getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.valueOf(0)) < 0) throw new InsufficientFundsException();
        setBalance(newBalance);
    }
}
