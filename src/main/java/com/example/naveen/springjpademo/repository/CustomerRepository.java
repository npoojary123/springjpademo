package com.example.naveen.springjpademo.repository;

import com.example.naveen.springjpademo.dto.OrderResponse;
import com.example.naveen.springjpademo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

   @Query("SELECT new com.example.naveen.springjpademo.dto.OrderResponse(c.name , p.productName) FROM Customer c JOIN c.products p")
    public List<OrderResponse> getJoinInformation();
}
