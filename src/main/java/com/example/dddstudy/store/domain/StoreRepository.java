package com.example.dddstudy.store.domain;

import java.util.Optional;

public interface StoreRepository {
    Optional<Store> findById(long id);
}
