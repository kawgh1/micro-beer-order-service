package com.kwgdev.microbeerorderservice.repositories;

import com.kwgdev.microbeerorderservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * created by kw on 1/14/2021 @ 7:21 PM
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByCustomerNameLike(String customerName);

    Optional<Customer> findByCustomerName(String name);
}
