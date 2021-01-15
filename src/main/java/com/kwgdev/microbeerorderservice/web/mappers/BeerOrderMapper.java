package com.kwgdev.microbeerorderservice.web.mappers;

/**
 * created by kw on 1/14/2021 @ 7:25 PM
 */
import com.kwgdev.microbeerorderservice.domain.BeerOrder;
import com.kwgdev.model.BeerOrderDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);

}