package com.bic.coffee_project.repository;

import com.bic.coffee_project.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, String> {

    // удалить несколько записей о заказе на основе определенных критериев, таких как все заказы с определенным статусом
//    @Transactional
//    void deleteByName(String name);
}