package com.kwgdev.brewery.model;

/**
 * created by kw on 1/14/2021 @ 7:18 PM
 */
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerOrderPagedList extends PageImpl<BeerOrderDto> {
    public BeerOrderPagedList(List<BeerOrderDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerOrderPagedList(List<BeerOrderDto> content) {
        super(content);
    }
}
