package com.example.dddstudy.menu.domain;

import java.util.Optional;

public interface MenuRepository {
    Optional<Menu> findById(long id);
}
