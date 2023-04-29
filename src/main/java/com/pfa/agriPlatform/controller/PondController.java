package com.pfa.agriPlatform.controller;
import com.pfa.agriPlatform.entity.Pond;
import com.pfa.agriPlatform.service.PondService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class PondController {
    private PondService ps;

    @GetMapping("/pond/{idClient}")
    public List<Pond> getPond(@PathVariable Long idClient){return ps.getAllPondByIdClient(idClient);}
    @Transactional
    @PostMapping("/pond")
    public void addPond(@RequestBody List<Pond> pond) {
        ps.addPond(pond);
    }

    @Transactional
    @PutMapping("/pond/{idClient}")
    public ResponseEntity<?> updatePond(@PathVariable("idClient") Long idClient, @RequestBody List<Pond> updatedPond) {
        // Check if the client exists
        List<Pond> pondList = ps.getAllPondByIdClient(idClient);
        if (!ps.existsByIdClient(idClient)) {
            // Return error response if client not found
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Client with id " + idClient + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Update the Pond entity with the provided data
        int i =0;
        for( Pond pond : pondList)
        {
            ps.updatePond(pond.getId(),
                    updatedPond.get(i).getIdClient(),
                    updatedPond.get(i).getCapacity(),
                    updatedPond.get(i).getBetan(),
                    updatedPond.get(i).getEquipment()
            );
            i++;
        }

        // Return success response
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Client with id " + idClient+ " updated successfully");
        return ResponseEntity.ok().body(message);
    }
    @DeleteMapping("/pond/{idClient}")
    public ResponseEntity<?> deletePond(@PathVariable("idClient") Long idClient) {
        if (ps.existsByIdClient(idClient)) {
            ps.deletePond(idClient);
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Client with id " + idClient + "deleted successfully");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", idClient + " client not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
