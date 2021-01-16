package com.kwgdev.brewery.microbeerorderservice.repositories;

/**
 * created by kw on 1/14/2021 @ 7:20 PM
 */
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrderLine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}
