package com.mycompagny.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired private ClientRepository repo;
    public List<Client> listAll(){
        return (List<Client>) repo.findAll();
    }

    public void save(Client client) {
        repo.save(client);
    }
    
    public Client get(Integer id) throws ClientNotFoundException {
        Optional<Client> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ClientNotFoundException("could not find any client with Id : " + id);
    }
    public void delete(Integer id) throws ClientNotFoundException {
        long count = repo.countById(id);
        if(count ==0 ){
        //if(count == null || count ==0 ){
            throw new ClientNotFoundException("could not find any client with Id : " + id);
        }
        repo.deleteById(id);
    }
}
