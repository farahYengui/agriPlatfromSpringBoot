package com.pfa.agriPlatform.service;

import com.pfa.agriPlatform.entity.General;
import com.pfa.agriPlatform.entity.Plan;
import com.pfa.agriPlatform.repository.PlanRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanService {
    @Autowired
    private final PlanRepo pr;

    public List<Plan> getAllPlanByIdClient(Long idClient) {
        return pr.findAllByIdClient(idClient);
    }
    @Transactional
    public void addPlan(List<Plan> plan){pr.saveAll(plan);}
    @Transactional
    public boolean existsByIdClient(Long idClient){return pr.existsByIdClient(idClient);}
    @Transactional
    public void deletePlan(Long idClient){
        pr.deleteAllByIdClient(idClient);
    }
    public void updatePlan(Long id, Long idClient, int sector, int area, int nature, int egg, int flowG, int flowSec)
    {
        pr.updatePlan(id, idClient, sector, area, nature, egg, flowG, flowSec);
    }
}
