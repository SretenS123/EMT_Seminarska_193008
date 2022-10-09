package com.example.cakecatalog.domain.repository;

import com.example.cakecatalog.domain.model.Cake;
import com.example.cakecatalog.domain.model.CakeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<Cake, CakeId> {
}
