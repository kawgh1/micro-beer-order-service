package com.kwgdev.brewery.microbeerorderservice.services;

/**
 * created by kw on 1/14/2021 @ 7:21 PM
 */
import com.kwgdev.brewery.model.BeerDto;
import com.kwgdev.brewery.model.BeerPagedList;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);

    Optional<BeerPagedList> getListofBeers();
}
