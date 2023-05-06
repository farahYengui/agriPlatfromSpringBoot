package com.pfa.agriPlatform.controller;
import com.pfa.agriPlatform.entity.Plan;
import com.pfa.agriPlatform.service.PlanService;
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
public class PlanController {
    private PlanService ps;

    @GetMapping("/plan/{idClient}")
    public List<Plan> getPlan(@PathVariable Long idClient){return ps.getAllPlanByIdClient(idClient);}
    @Transactional
    @PostMapping("/plan")
    public void addPlan(@RequestBody List<Plan> plan) {
        ps.addPlan(plan);
    }

    @Transactional
    @PutMapping("/plan/{idClient}")
    public ResponseEntity<?> updatePlan(@PathVariable("idClient") Long idClient, @RequestBody List<Plan> updatedPlan) {
        // Check if the client exists
        List<Plan> planList = ps.getAllPlanByIdClient(idClient);
        if (!ps.existsByIdClient(idClient)) {
            // Return error response if client not found
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Client with id " + idClient + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Update the Plan entity with the provided data
        int i =0;
        for( Plan plan : planList)
        {
            ps.updatePlan(plan.getId(),
                    updatedPlan.get(i).getIdClient(),
                    updatedPlan.get(i).getSector(),
                    updatedPlan.get(i).getArea(),
                    updatedPlan.get(i).getNature(),
                    updatedPlan.get(i).getEgg(),
                    updatedPlan.get(i).getFlowG(),
                    updatedPlan.get(i).getFlowSec()

            );
            i++;
        }

        // Return success response
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Client with id " + idClient+ " updated successfully");
        return ResponseEntity.ok().body(message);
    }
    @DeleteMapping("/plan/{idClient}")
    public ResponseEntity<?> deletePlan(@PathVariable("idClient") Long idClient) {
        if (ps.existsByIdClient(idClient)) {
            ps.deletePlan(idClient);
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Client with id " + idClient + "deleted successfully");
            return ResponseEntity.ok().body(message);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", idClient + " client not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
