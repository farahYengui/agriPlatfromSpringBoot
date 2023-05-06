package com.pfa.agriPlatform.repository;

import com.pfa.agriPlatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsersRepo extends JpaRepository<User,Long> {
    @Modifying
    @Query("UPDATE User u SET u.username = :username, u.description = :description, u.email = :email, u.fName = :fName, u.lName = :lName, u.password = :password, u.address = :address, u.city = :city, u.country = :country, u.code = :code, u.fb = :fb, u.insta = :insta, u.linkedin = :linkedin WHERE u.id = :id")
    void updateUser(@Param("id") Long id, @Param("username") String username, @Param("description") String description, @Param("email") String email, @Param("fName") String fName,@Param("lName") String lName,@Param("password") String password,@Param("address") String address,@Param("city") String city,@Param("country") String country,@Param("code") int code,@Param("fb") String fb,@Param("insta") String insta,@Param("linkedin") String linkedin);
    User findByUsername(String username);
}
