package com.pfa.agriPlatform.repository;

import com.pfa.agriPlatform.entity.General;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface GeneralRepo extends JpaRepository<General,Long> {
    @Query("SELECT g FROM General g WHERE g.idClient = :idClient")
    List<General> findAllByIdClient(@Param("idClient") Long idClient);
    @Modifying
    @Query("UPDATE General g SET g.idClient = :idClient, g.kind = :kind, g.variety = :variety, g.eFeet = :eFeet, g.eLine = :eLine, g.year = :year, g.size = :size WHERE g.id = :id")
    void updateGeneral(@Param("id") Long id, @Param("idClient") Long idClient, @Param("kind") String kind, @Param("variety") String variety, @Param("eFeet") int eFeet, @Param("eLine") int eLine, @Param("year") int year, @Param("size") int size);
    @Transactional
    void deleteAllByIdClient(Long idClient);
    @Transactional
    boolean existsByIdClient(Long idClient);

}

