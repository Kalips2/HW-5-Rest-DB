package com.example.hw4restdb.repositories;

import com.example.hw4restdb.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepo extends JpaRepository<Goods, Long> {
}
