package com.example.hw4restdb.repositories;

import com.example.hw4restdb.model.Holds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Holds Repository.
 * */
@Repository
public interface HoldsRepo extends JpaRepository<Holds, Long> {

  @Query("SELECT holds FROM Holds holds WHERE holds.warehouse_code = :warehouse_code AND holds.goods_code = :goods_code")
  Holds findByWarehouse_codeAndGoods_code(Long warehouse_code, Long goods_code);

}
