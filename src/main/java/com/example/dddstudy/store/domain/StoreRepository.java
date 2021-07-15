package com.example.dddstudy.store.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface StoreRepository extends Repository<Store, Long> {
    Optional<Store> findById(long id);
}
