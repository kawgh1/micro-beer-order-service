package com.kwgdev.brewery.microbeerorderservice.domain;

/**
 * created by kw on 1/14/2021 @ 7:20 PM
 */
public enum BeerOrderStatusEnum {
    NEW, CANCELLED, PENDING_VALIDATION, VALIDATED, VALIDATION_EXCEPTION,
    PENDING_ALLOCATION, ALLOCATED, ALLOCATION_ERROR, PENDING_INVENTORY,
    PICKED_UP, DELIVERED, DELIVERY_EXCEPTION

}
