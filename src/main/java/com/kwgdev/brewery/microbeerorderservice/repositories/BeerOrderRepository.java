package com.kwgdev.brewery.microbeerorderservice.repositories;

import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrder;
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrderStatusEnum;
import com.kwgdev.brewery.microbeerorderservice.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * created by kw on 1/14/2021 @ 7:21 PM
 */
public interface BeerOrderRepository  extends JpaRepository<BeerOrder, UUID> {

    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);

    List<BeerOrder> findAllByOrderStatus(BeerOrderStatusEnum orderStatusEnum);
}
