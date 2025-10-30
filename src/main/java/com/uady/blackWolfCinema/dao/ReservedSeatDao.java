package com.uady.blackWolfCinema.dao;

import java.util.List;

import com.uady.blackWolfCinema.model.ReservedSeat;

public interface ReservedSeatDao {
    void save(ReservedSeat reservedSeat);
    void delete(ReservedSeat reservedSeat);
    List<ReservedSeat> findExpiredSeats();
    List<ReservedSeat> findByShowId(int showId);
    List<ReservedSeat> findBySessionId(String sessionId);
}
