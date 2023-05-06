package com.pfa.agriPlatform.controller;

import com.pfa.agriPlatform.entity.User;
import com.pfa.agriPlatform.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UsersController {
    private UsersService us;
    @GetMapping("/users")
    public List<User> getUsers(){return us.getUsers();}
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){return us.getUserById(id).orElseThrow(
                ()-> new EntityNotFoundException("Requested user not found")
        );

    }
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return us.addUser(user);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        if (us.existsById(id)) {
            User u1 = us.getUserById(id).orElseThrow(
                    () -> new EntityNotFoundException("Requested user not found")
            );
            u1.setUsername(user.getUsername());
            u1.setDescription(user.getDescription());
            u1.setEmail(user.getEmail());
            u1.setFName(user.getFName());
            u1.setLName(user.getLName());
            u1.setPassword(user.getPassword());
            u1.setAddress(user.getAddress());
            u1.setCountry(user.getCountry());
            u1.setCity(user.getCity());
            u1.setCode(user.getCode());
            u1.setFb(user.getFb());
            u1.setInsta(user.getInsta());
            u1.setLinkedin(user.getLinkedin());
            us.addUser(u1);
            return ResponseEntity.ok().body(u1);
        }
        else {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id + "user not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        if (us.existsById(id)) {
            us.deleteUser(id);
            HashMap<String,String> message = new HashMap<>();
            message.put("message", "User with id " + id + "deleted successfully");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        else {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id + "user not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }

}
