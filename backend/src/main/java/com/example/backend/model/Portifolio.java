package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Portifolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer shares;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Stock stock;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}
