package com.uady.blackWolfCinema.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reserved_seats")
public class ReservedSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "seat_number")
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "idshow", referencedColumnName = "idshow")
    private Show show;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "reserved_at")
    private LocalDateTime reservedAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public ReservedSeat(String seatNumber, Show show, String sessionId, LocalDateTime reservedAt, LocalDateTime expiresAt) {
        this.seatNumber = seatNumber;
        this.show = show;
        this.sessionId = sessionId;
        this.reservedAt = reservedAt;
        this.expiresAt = expiresAt;
    }
}
