package com.example.naveen.springjpademo.repository;

import com.example.naveen.springjpademo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
