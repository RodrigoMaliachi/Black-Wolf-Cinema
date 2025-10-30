package com.uady.blackWolfCinema.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.uady.blackWolfCinema.service.ReservedSeatService;

@Component
@EnableScheduling
public class SeatReleaseScheduler {

    private ReservedSeatService reservedSeatService;

    @Autowired
    public SeatReleaseScheduler(ReservedSeatService theReservedSeatService) {
        this.reservedSeatService = theReservedSeatService;
    }

    /**
     * Ejecuta cada minuto para liberar asientos apartados que han expirado
     * Los asientos se liberan 5 minutos después de ser apartados
     */
    @Scheduled(fixedDelay = 60000) // 60 segundos = 1 minuto
    public void releaseExpiredSeats() {
        try {
            reservedSeatService.releaseExpiredSeats();
        } catch (Exception e) {
            // Log the exception but don't crash the scheduler
            System.err.println("Error liberando asientos expirados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
