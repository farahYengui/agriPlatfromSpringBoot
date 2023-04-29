package com.pfa.agriPlatform.service;

import com.pfa.agriPlatform.entity.General;
import com.pfa.agriPlatform.repository.GeneralRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GeneralService {
    @Autowired
    private final GeneralRepo gr;

    public List<General> getAllGeneralByIdClient(Long idClient) {
        return gr.findAllByIdClient(idClient);
    }
    @Transactional
    public void addGeneral(List<General> general){gr.saveAll(general);}
    @Transactional
    public boolean existsByIdClient(Long idClient){return gr.existsByIdClient(idClient);}
    @Transactional
    public void deleteGeneral(Long idClient){
        gr.deleteAllByIdClient(idClient);
    }
    public void updateGeneral(Long id, Long idClient,String kind, String variety, int eFeet, int eLine, int year, int size)
    {
        gr.updateGeneral(id, idClient,kind, variety, eFeet, eLine, year, size);
    }
}
