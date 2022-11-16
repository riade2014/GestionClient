package com.mycompagny.client;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface CrudRepository<T,ID> extends Repository {
    <S extends Client> S save(S entity);
    <S extends Client> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Client> findById(Integer integer);

    boolean existsById(Integer integer);

    Iterable<Client> findAll();

    Iterable<Client> findAllById(Iterable<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(Client entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Client> entities);

    void deleteAll();
}
