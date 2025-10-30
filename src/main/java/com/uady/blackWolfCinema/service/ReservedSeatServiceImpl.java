package com.uady.blackWolfCinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uady.blackWolfCinema.dao.ReservedSeatDao;
import com.uady.blackWolfCinema.model.ReservedSeat;

@Service
public class ReservedSeatServiceImpl implements ReservedSeatService {

    private ReservedSeatDao reservedSeatDao;

    @Autowired
    public ReservedSeatServiceImpl(ReservedSeatDao theReservedSeatDao) {
        this.reservedSeatDao = theReservedSeatDao;
    }

    @Override
    public void save(ReservedSeat reservedSeat) {
        reservedSeatDao.save(reservedSeat);
    }

    @Override
    public void delete(ReservedSeat reservedSeat) {
        reservedSeatDao.delete(reservedSeat);
    }

    @Override
    public List<ReservedSeat> findExpiredSeats() {
        return reservedSeatDao.findExpiredSeats();
    }

    @Override
    public List<ReservedSeat> findByShowId(int showId) {
        return reservedSeatDao.findByShowId(showId);
    }

    @Override
    public List<ReservedSeat> findBySessionId(String sessionId) {
        return reservedSeatDao.findBySessionId(sessionId);
    }

    @Override
    public void releaseExpiredSeats() {
        List<ReservedSeat> expiredSeats = findExpiredSeats();
        for (ReservedSeat seat : expiredSeats) {
            delete(seat);
        }
    }
}
