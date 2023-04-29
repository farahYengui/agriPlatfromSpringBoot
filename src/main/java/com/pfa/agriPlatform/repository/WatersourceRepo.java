package com.pfa.agriPlatform.repository;

import com.pfa.agriPlatform.entity.Watersource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface WatersourceRepo extends JpaRepository<Watersource,Long> {
    @Query("SELECT w FROM Watersource w WHERE w.idClient = :idClient")
    List<Watersource> findAllByIdClient(@Param("idClient") Long idClient);
    @Modifying
    @Query("UPDATE Watersource w SET w.idClient = :idClient, w.depth = :depth, w.flow = :flow, w.salinity = :salinity, w.equipment = :equipment WHERE w.id = :id")
    void updateWatersource(@Param("id") Long id, @Param("idClient") Long idClient, @Param("depth") float depth, @Param("flow") float flow, @Param("salinity") float salinity, @Param("equipment") String equipment);
    @Transactional
    void deleteAllByIdClient(Long idClient);
    @Transactional
    boolean existsByIdClient(Long idClient);
}