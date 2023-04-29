package com.pfa.agriPlatform.service;

import com.pfa.agriPlatform.entity.Pond;
import com.pfa.agriPlatform.repository.PondRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PondService {
    @Autowired
    private final PondRepo pr;

    public List<Pond> getAllPondByIdClient(Long idClient) {
        return pr.findAllByIdClient(idClient);
    }
    @Transactional
    public void addPond(List<Pond> pond){pr.saveAll(pond);}
    @Transactional
    public boolean existsByIdClient(Long idClient){return pr.existsByIdClient(idClient);}
    @Transactional
    public void deletePond(Long idClient){pr.deleteAllByIdClient(idClient);}
    public void updatePond(Long id, Long idClient, float capacity, String betan, String equipment)
    {
        pr.updatePond(id, idClient, capacity,betan, equipment);
    }
}
