package com.kwgdev.microbeerorderservice.services;

/**
 * created by kw on 1/14/2021 @ 7:21 PM
 */
import com.kwgdev.microbeerorderservice.domain.BeerOrder;
import com.kwgdev.model.BeerOrderDto;

import java.util.UUID;

/**
 * Created by jt on 2019-09-08.
 */
public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    void beerOrderPassedValidation(UUID beerOrderId);

    void beerOrderFailedValidation(UUID beerOrderId);

    void beerOrderAllocationPassed(BeerOrderDto beerOrder);

    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrder);

    void beerOrderAllocationFailed(BeerOrderDto beerOrder);

    void pickupBeerOrder(UUID beerOrderId);

    void cancelOrder(UUID beerOrderId);
}
