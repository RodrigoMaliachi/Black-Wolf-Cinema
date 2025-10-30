package com.uady.blackWolfCinema.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uady.blackWolfCinema.model.ReservedSeat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ReservedSeatDaoImpl implements ReservedSeatDao {

    private EntityManager entityManager;

    @Autowired
    public ReservedSeatDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(ReservedSeat reservedSeat) {
        entityManager.merge(reservedSeat);
    }

    @Override
    @Transactional
    public void delete(ReservedSeat reservedSeat) {
        entityManager.remove(entityManager.merge(reservedSeat));
    }

    @Override
    public List<ReservedSeat> findExpiredSeats() {
        TypedQuery<ReservedSeat> theQuery = entityManager.createQuery(
            "from ReservedSeat where expiresAt < :now",
            ReservedSeat.class);
        theQuery.setParameter("now", LocalDateTime.now());
        return theQuery.getResultList();
    }

    @Override
    public List<ReservedSeat> findByShowId(int showId) {
        TypedQuery<ReservedSeat> theQuery = entityManager.createQuery(
            "from ReservedSeat where show.showId = :showId",
            ReservedSeat.class);
        theQuery.setParameter("showId", showId);
        return theQuery.getResultList();
    }

    @Override
    public List<ReservedSeat> findBySessionId(String sessionId) {
        TypedQuery<ReservedSeat> theQuery = entityManager.createQuery(
            "from ReservedSeat where sessionId = :sessionId",
            ReservedSeat.class);
        theQuery.setParameter("sessionId", sessionId);
        return theQuery.getResultList();
    }
}
