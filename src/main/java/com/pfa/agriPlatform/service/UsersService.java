package com.pfa.agriPlatform.service;

import com.pfa.agriPlatform.entity.User;
import com.pfa.agriPlatform.repository.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    @Autowired
    private final UsersRepo ur;
    public List<User> getUsers() {
        return ur.findAll();
    }
    public Optional<User> getUserById(Long id){return ur.findById(id);}
    public User addUser(User user){return ur.saveAndFlush(user);}
    public boolean existsById(Long id){return ur.existsById(id);}
    public void deleteUser(Long id){ur.deleteById(id);}
    public void updateUser(Long id, String username,String description, String email, String fName, String lName, String password, String address, String city, String country, int code)
    {
        ur.updateUser(id, username,description, email, fName, lName, password, address, city, country,code);
    }



}
