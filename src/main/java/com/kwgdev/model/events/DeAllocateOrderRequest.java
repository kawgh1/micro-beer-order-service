package com.kwgdev.model.events;

/**
 * created by kw on 1/14/2021 @ 7:16 PM
 */

import com.kwgdev.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeAllocateOrderRequest {
    private BeerOrderDto beerOrder;
}