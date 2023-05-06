package com.pfa.agriPlatform.controller;
import com.pfa.agriPlatform.entity.Client;
import com.pfa.agriPlatform.service.ClientsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class ClientsController {
    private ClientsService cs;
    @GetMapping("/clients")
    public List<Client> getClients(){return cs.getClients();}
    @GetMapping("/clients/{name}")
    public Client getClient(@PathVariable String name){return cs.getClientByName(name);}
    @PostMapping("/clients")
    public Client addClient(@RequestBody Client client) {
        return cs.addClient(client);
    }

    @Transactional
    @PutMapping("/clients/{name}")
    public ResponseEntity<?> updateClient(@PathVariable("name") String name, @RequestBody Client updatedClient) {
        // Check if the client exists
        Client client = cs.getClientByName(name);
        if (!cs.existsByName(name)) {
            // Return error response if client not found
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Client named " + name + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        cs.updateClient(client.getId(), updatedClient.getName(), updatedClient.getEmail(), updatedClient.getCity(),
                updatedClient.getAddress(), updatedClient.getIrrigated(), updatedClient.getNoirrigated(),
                updatedClient.getTotal(), updatedClient.getHomogenous(), updatedClient.getHeterogenous(),
                updatedClient.getClay(), updatedClient.getSilt(), updatedClient.getSand(),
                updatedClient.getLimestone(), updatedClient.getGypsum(), updatedClient.getMaps());

        // Return success response
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Client named " + name + " updated successfully");
        return ResponseEntity.ok().body(message);
    }
    @Transactional
    @DeleteMapping("/clients/{name}")
    public ResponseEntity<?> deleteClient(@PathVariable("name") String name) {
        if (cs.existsByName(name)) {
            cs.deleteClient(name);
            HashMap<String,String> message = new HashMap<>();
            message.put("message", "Client named " + name + " deleted successfully");
            return ResponseEntity.ok().body(message);
        }
        else {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", name + " client not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }
    @GetMapping("/clients/names")
    public List<String> getAllClientNames() {
        return cs.getAllClientNames();
    }

}
