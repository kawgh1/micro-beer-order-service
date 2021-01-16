package com.kwgdev.brewery.microbeerorderservice.web.mappers;

/**
 * created by kw on 1/14/2021 @ 7:25 PM
 */
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrderLine;
import com.kwgdev.brewery.model.BeerOrderLineDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);

}
