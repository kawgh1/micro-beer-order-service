package com.kwgdev.brewery.microbeerorderservice.domain;

/**
 * created by kw on 1/14/2021 @ 7:20 PM
 */
public enum  BeerOrderEventEnum {
    CANCEL_ORDER, VALIDATE_ORDER, VALIDATION_PASSED, VALIDATION_FAILED,
    ALLOCATE_ORDER, ALLOCATION_SUCCESS, ALLOCATION_FAILED, ALLOCATION_NO_INVENTORY,
    BEER_ORDER_PICKED_UP
}
