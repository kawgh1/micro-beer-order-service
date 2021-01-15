package com.kwgdev.microbeerorderservice.services;

/**
 * created by kw on 1/14/2021 @ 7:45 PM
 */
import com.kwgdev.model.BeerOrderDto;
import com.kwgdev.model.BeerOrderLineDto;
import com.kwgdev.model.BeerOrderPagedList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ComponentScan(basePackages = {"guru.sfg.brewery.order.service.services", "guru.sfg.brewery.order.service.web.mappers"})
class BeerOrderServiceImplTest extends BaseServiceTest {

    @MockBean
    BeerService beerService;

    @Test
    void listOrders() {

        //make sure we have two orders
        assertThat(beerOrderRepository.count()).isGreaterThanOrEqualTo(3L);

        BeerOrderPagedList pagedList = beerOrderService.listOrders(testCustomer.getId(), PageRequest.of(0, 25));

        assertThat(pagedList.getTotalElements()).isGreaterThanOrEqualTo(3L);
        assertThat(pagedList.getContent().size()).isGreaterThanOrEqualTo(3);
    }

    @Test
    void placeOrder() {
        BeerOrderDto dto = BeerOrderDto.builder()
                .orderStatusCallbackUrl("http://foo.com")
                .beerOrderLines(Arrays.asList(BeerOrderLineDto
                        .builder().beerId(testBeerGalaxy.getId()).orderQuantity(12).build()))
                .build();

        BeerOrderDto placedOrder = beerOrderService.placeOrder(testCustomer.getId(), dto);

        assertThat(placedOrder.getId()).isNotNull();
        assertThat(placedOrder.getOrderStatus()).isEqualToIgnoringCase("NEW");
    }

    @Transactional
    @Test
    void getOrderById() {
        BeerOrderDto dto = beerOrderService.getOrderById(testCustomer.getId(), testOrder1.getId());

        assertThat(dto.getId()).isEqualTo(testOrder1.getId());
    }

    @Transactional
    @Test
    void pickupOrder() {
        beerOrderService.pickupOrder(testCustomer.getId(), testOrder1.getId());

        BeerOrderDto dto = beerOrderService.getOrderById(testCustomer.getId(), testOrder1.getId());

        assertThat(dto.getId()).isEqualTo(testOrder1.getId());
        assertThat(dto.getOrderStatus()).isEqualTo("PICKED_UP");
    }
}
