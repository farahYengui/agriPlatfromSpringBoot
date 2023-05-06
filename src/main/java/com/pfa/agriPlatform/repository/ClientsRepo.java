package com.pfa.agriPlatform.repository;

import com.pfa.agriPlatform.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface ClientsRepo extends JpaRepository<Client,Long> {
    Client findFirstByName(String name);
    boolean existsByName(String name);
    @Modifying
    @Transactional
    @Query("DELETE FROM Client c WHERE c.name = :name")
    void deleteByName(@Param("name") String name);
    @Modifying
    @Query("UPDATE Client c SET c.name = :name, c.email = :email, c.city = :city, c.address = :address, c.irrigated = :irrigated, c.noirrigated = :noirrigated, c.total = :total, c.homogenous = :homogenous, c.heterogenous = :heterogenous, c.clay = :clay, c.silt = :silt, c.sand = :sand, c.limestone = :limestone, c.gypsum = :gypsum, c.maps = :maps WHERE c.id = :id")
    void updateClient(@Param("id") Long id, @Param("name") String name, @Param("email") String email, @Param("city") String city, @Param("address") String address, @Param("irrigated") int irrigated, @Param("noirrigated") int noirrigated, @Param("total") int total, @Param("homogenous") int homogenous,@Param("heterogenous") int heterogenous, @Param("clay") int clay, @Param("silt") int silt, @Param("sand") int sand, @Param("limestone") int limestone, @Param("gypsum") int gypsum, @Param("maps") String maps);
    @Query("SELECT c.name FROM Client c")
    List<String> findAllClientNames();

}
