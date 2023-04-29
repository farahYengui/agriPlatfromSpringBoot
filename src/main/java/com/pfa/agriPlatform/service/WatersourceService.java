package com.pfa.agriPlatform.service;

import com.pfa.agriPlatform.entity.Plan;
import com.pfa.agriPlatform.entity.Watersource;
import com.pfa.agriPlatform.repository.WatersourceRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class WatersourceService {
    @Autowired
    private final WatersourceRepo wr;

    public List<Watersource> getAllWatersourceByIdClient(Long idClient) {
        return wr.findAllByIdClient(idClient);
    }
    @Transactional
    public void addWatersource(List<Watersource> watersource){wr.saveAll(watersource);}
    @Transactional
    public boolean existsByIdClient(Long idClient){return wr.existsByIdClient(idClient);}
    @Transactional
    public void deleteWatersource(Long idClient){wr.deleteAllByIdClient(idClient);}
    public void updateWatersource(Long id, Long idClient, float depth, float flow, float salinity, String equipment)
    {
        wr.updateWatersource(id, idClient, depth,flow,salinity, equipment);
    }
}
