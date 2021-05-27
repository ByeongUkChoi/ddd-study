package com.example.dddstudy.menu.domain;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Optional<Menu> findById(long id);
    List<Menu> findAllById(Iterable<Long> ids);
}
