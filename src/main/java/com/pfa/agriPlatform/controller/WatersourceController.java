package com.pfa.agriPlatform.controller;
import com.pfa.agriPlatform.entity.Watersource;
import com.pfa.agriPlatform.service.WatersourceService;
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
public class WatersourceController {
    private WatersourceService ws;

    @GetMapping("/watersource/{idClient}")
    public List<Watersource> getWatersource(@PathVariable Long idClient){return ws.getAllWatersourceByIdClient(idClient);}
    @Transactional
    @PostMapping("/watersource")
    public void addWatersource(@RequestBody List<Watersource> watersource) {
        ws.addWatersource(watersource);
    }

    @Transactional
    @PutMapping("/watersource/{idClient}")
    public ResponseEntity<?> updateWatersource(@PathVariable("idClient") Long idClient, @RequestBody List<Watersource> updatedWatersource) {
        // Check if the client exists
        List<Watersource> watersourceList = ws.getAllWatersourceByIdClient(idClient);
        if (!ws.existsByIdClient(idClient)) {
            // Return error response if client not found
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Client with id " + idClient + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Update the Watersource entity with the provided data
        int i =0;
        for( Watersource watersource : watersourceList)
        {
            ws.updateWatersource(watersource.getId(),
                    updatedWatersource.get(i).getIdClient(),
                    updatedWatersource.get(i).getDepth(),
                    updatedWatersource.get(i).getFlow(),
                    updatedWatersource.get(i).getSalinity(),
                    updatedWatersource.get(i).getEquipment()
            );
            i++;
        }

        // Return success response
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Client with id " + idClient+ " updated successfully");
        return ResponseEntity.ok().body(message);
    }
    @DeleteMapping("/watersource/{idClient}")
    public ResponseEntity<?> deleteWatersource(@PathVariable("idClient") Long idClient) {
        if (ws.existsByIdClient(idClient)) {
            ws.deleteWatersource(idClient);
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
