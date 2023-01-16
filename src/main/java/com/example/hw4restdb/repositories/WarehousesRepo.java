package com.example.hw4restdb.repositories;

import com.example.hw4restdb.model.Warehouses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Warehouses Repository.
 */
@Repository
public interface WarehousesRepo extends JpaRepository<Warehouses, Long> {

}
