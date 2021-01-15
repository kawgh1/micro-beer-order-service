package com.kwgdev.model.events;

import com.kwgdev.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by kw on 1/14/2021 @ 7:16 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateBeerOrderRequest {

    private BeerOrderDto beerOrder;
}
