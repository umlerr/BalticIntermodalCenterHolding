package com.bic.project.repository;


import com.bic.project.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContainerRepository extends CrudRepository<Container, String> {
    // удалить несколько записей о заказе на основе определенных критериев, таких как все заказы с определенным статусом
//    @Transactional
//    void deleteByName(String name);
    @Transactional
    default void updateOrInsert(Container container) {
        save(container);
    }


    /*
    Когда будет много полей

    @Query("select c from Coffee c where lower(c.field1) like %:keyword% " +
            "or lower(c.field2) like %:keyword% " +
            "or lower(c.field3) like %:keyword% ")
     */

    @Query("select c from Container c where lower(c.Number) like %:keyword% ")
    List<Container> searchByKeyword(@Param("keyword") String keyword);
}