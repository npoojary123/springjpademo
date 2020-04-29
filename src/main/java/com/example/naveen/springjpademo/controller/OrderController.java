package com.example.naveen.springjpademo.controller;



import com.example.naveen.springjpademo.dto.OrderRequest;
import com.example.naveen.springjpademo.dto.OrderResponse;
import com.example.naveen.springjpademo.entity.Customer;
import com.example.naveen.springjpademo.entity.Product;
import com.example.naveen.springjpademo.exception.ResourceNotFoundException;
import com.example.naveen.springjpademo.repository.CustomerRepository;
import com.example.naveen.springjpademo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody OrderRequest request) {
        return customerRepository.save(request.getCustomer());
    }

    @GetMapping("/findAllOrders")
    public List<Customer> findAllOrders() {
        return customerRepository.findAll();
    }

    @GetMapping("/getInfo")
    public List<OrderResponse> getJoinInformation() {
        return customerRepository.getJoinInformation();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Customer> getOrderById(@PathVariable(value = "id") Integer customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        return ResponseEntity.ok().body(customer);

    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Customer> updateOrder(@PathVariable(value = "id") Integer customerId,
                                                   @Valid @RequestBody OrderRequest request) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customer.setName(request.getCustomer().getName());
        customer.setEmail(request.getCustomer().getEmail());
        customer.setGender(request.getCustomer().getGender());
        customer.setProducts(request.getCustomer().getProducts());
        final Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }


    @DeleteMapping("/order/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Integer customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
