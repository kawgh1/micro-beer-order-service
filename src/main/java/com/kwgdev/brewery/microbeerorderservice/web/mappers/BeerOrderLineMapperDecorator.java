package com.kwgdev.brewery.microbeerorderservice.web.mappers;

/**
 * created by kw on 1/14/2021 @ 7:25 PM
 */
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrderLine;
import com.kwgdev.brewery.microbeerorderservice.services.BeerService;
import com.kwgdev.brewery.model.BeerDto;
import com.kwgdev.brewery.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerById(line.getBeerId());

        beerDtoOptional.ifPresent(beerDto -> {
            orderLineDto.setBeerName(beerDto.getBeerName());
            orderLineDto.setBeerStyle(beerDto.getBeerName());
            orderLineDto.setUpc(beerDto.getUpc());
            orderLineDto.setPrice(beerDto.getPrice());
        });

        return orderLineDto;
    }
}
