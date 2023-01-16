package com.example.hw4restdb.repositories;

import com.example.hw4restdb.model.Goods;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Goods Repository.
 */
@Repository
public interface GoodsRepo extends JpaRepository<Goods, Long> {

  //@Query("SELECT goods FROM Goods goods WHERE goods.manufacturers_code = :code AND goods.name LIKE %:name%")
  List<Goods> findByNameContainsAndManufacturers_code(String name, Long code,
                                                      Pageable pageable);
}
