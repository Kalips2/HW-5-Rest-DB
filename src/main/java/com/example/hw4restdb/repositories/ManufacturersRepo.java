package com.example.hw4restdb.repositories;

import com.example.hw4restdb.model.Manufacturers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturersRepo extends JpaRepository<Manufacturers, Long> {
}
