package com.pfa.agriPlatform.repository;

import com.pfa.agriPlatform.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PlanRepo extends JpaRepository<Plan,Long> {
    @Query("SELECT p FROM Plan p WHERE p.idClient = :idClient")
    List<Plan> findAllByIdClient(@Param("idClient") Long idClient);
    @Modifying
    @Query("UPDATE Plan p SET p.idClient = :idClient, p.sector = :sector, p.area = :area, p.nature = :nature, p.egg = :egg, p.flowG = :flowG, p.flowSec = :flowSec WHERE p.id = :id")
    void updatePlan(@Param("id") Long id, @Param("idClient") Long idClient, @Param("sector") int sector, @Param("area") int area, @Param("nature") int nature, @Param("egg") int egg, @Param("flowG") int flowG, @Param("flowSec") int flowSec);
    @Transactional
    void deleteAllByIdClient(Long idClient);
    @Transactional
    boolean existsByIdClient(Long idClient);
}