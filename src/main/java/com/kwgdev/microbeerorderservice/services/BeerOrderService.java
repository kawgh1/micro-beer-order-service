package com.kwgdev.microbeerorderservice.services;

/**
 * created by kw on 1/14/2021 @ 7:21 PM
 */
import com.kwgdev.model.BeerOrderDto;
import com.kwgdev.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickupOrder(UUID customerId, UUID orderId);
}
