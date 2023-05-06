package com.pfa.agriPlatform.service;

import com.pfa.agriPlatform.entity.Client;
import com.pfa.agriPlatform.repository.ClientsRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientsService {
    @Autowired
    private final ClientsRepo cr;

    public List<Client> getClients() {
        return cr.findAll();
    }
    public Client getClientByName(String name){return cr.findFirstByName(name);}
    public Client addClient(Client client){return cr.saveAndFlush(client);}
    public boolean existsByName(String name){return cr.existsByName(name);}
    @Transactional
    public void deleteClient(String name){cr.deleteByName(name);}
    public void updateClient(Long id, String name, String email, String city, String address, int irrigated, int noirrigated, int total, int homogenous, int heterogenous, int clay, int silt, int sand, int limestone, int gypsum, String maps)
    {
        cr.updateClient(id, name, email, city, address, irrigated, noirrigated, total, homogenous, heterogenous, clay, silt, sand, limestone, gypsum, maps);
    }
    public List<String> getAllClientNames(){return cr.findAllClientNames();}
}
