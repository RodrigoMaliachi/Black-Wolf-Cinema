package com.uady.blackWolfCinema.service;

import java.util.List;

import com.uady.blackWolfCinema.model.ReservedSeat;

public interface ReservedSeatService {
    void save(ReservedSeat reservedSeat);
    void delete(ReservedSeat reservedSeat);
    List<ReservedSeat> findExpiredSeats();
    List<ReservedSeat> findByShowId(int showId);
    List<ReservedSeat> findBySessionId(String sessionId);
    void releaseExpiredSeats();
}
