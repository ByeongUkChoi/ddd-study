package com.example.dddstudy.menu.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends Repository<Menu, Long> {
    Optional<Menu> findById(long id);
    List<Menu> findAllById(Iterable<Long> ids);
}
