package com.mycompagny.client;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client,Integer> {
    public long countById(Integer id);
}
