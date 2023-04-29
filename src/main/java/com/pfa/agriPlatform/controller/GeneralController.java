package com.pfa.agriPlatform.controller;
import com.pfa.agriPlatform.entity.General;
import com.pfa.agriPlatform.service.GeneralService;
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
public class GeneralController {
    private GeneralService gs;

    @GetMapping("/general/{idClient}")
    public List<General> getGeneral(@PathVariable Long idClient){return gs.getAllGeneralByIdClient(idClient);}
    @Transactional
    @PostMapping("/general")
    public void addGeneral(@RequestBody List<General> general) {
         gs.addGeneral(general);
    }
    @Transactional
    @PutMapping("/general/{idClient}")
    public ResponseEntity<?> updateGeneral(@PathVariable("idClient") Long idClient, @RequestBody List<General> updatedGeneral) {
        // Check if the client exists
        List<General> generalList = gs.getAllGeneralByIdClient(idClient);
        if (!gs.existsByIdClient(idClient)) {
            // Return error response if client not found
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Client with id " + idClient + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Update the General entity with the provided data
        int i =0;
        for( General general : generalList)
        {
            gs.updateGeneral(general.getId(),
                    updatedGeneral.get(i).getIdClient(),
                    updatedGeneral.get(i).getKind(),
                    updatedGeneral.get(i).getVariety(),
                    updatedGeneral.get(i).getEFeet(),
                    updatedGeneral.get(i).getELine(),
                    updatedGeneral.get(i).getYear(),
                    updatedGeneral.get(i).getSize()
            );
            i++;
        }


        // Return success response
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Client with id " + idClient+ " updated successfully");
        return ResponseEntity.ok().body(message);
    }
    @Transactional
    @DeleteMapping("/general/{idClient}")
    public ResponseEntity<?> deleteGeneral(@PathVariable("idClient") Long idClient) {
        if (gs.existsByIdClient(idClient)) {
            gs.deleteGeneral(idClient);
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

