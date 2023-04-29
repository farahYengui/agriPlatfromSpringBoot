package com.pfa.agriPlatform.repository;

import com.pfa.agriPlatform.entity.Pond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PondRepo extends JpaRepository<Pond,Long> {
    @Query("SELECT p FROM Pond p WHERE p.idClient = :idClient")
    List<Pond> findAllByIdClient(@Param("idClient") Long idClient);
    @Modifying
    @Query("UPDATE Pond p SET p.idClient = :idClient, p.capacity = :capacity, p.betan = :betan, p.equipment = :equipment WHERE p.id = :id")
    void updatePond(@Param("id") Long id, @Param("idClient") Long idClient, @Param("capacity") float capacity, @Param("betan") String betan, @Param("equipment") String equipment);
    @Transactional
    void deleteAllByIdClient(Long idClient);
    @Transactional
    boolean existsByIdClient(Long idClient);
}