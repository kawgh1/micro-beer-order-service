package com.kwgdev.brewery.microbeerorderservice.statemachine.actions;

/**
 * created by kw on 1/14/2021 @ 7:23 PM
 */
import com.kwgdev.brewery.microbeerorderservice.config.JmsConfig;
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrder;
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrderEventEnum;
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrderStatusEnum;
import com.kwgdev.brewery.microbeerorderservice.services.BeerOrderManagerImpl;
import com.kwgdev.brewery.microbeerorderservice.web.mappers.BeerOrderMapper;
import com.kwgdev.brewery.model.events.AllocateBeerOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 2019-09-08.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AllocateBeerOrder implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderMapper beerOrderMapper;


    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {

        log.debug("Sending Allocation Request...");

        BeerOrder beerOrder = context.getStateMachine().getExtendedState()
                .get(BeerOrderManagerImpl.ORDER_OBJECT_HEADER, BeerOrder.class);

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE, AllocateBeerOrderRequest
                .builder()
                .beerOrder(beerOrderMapper.beerOrderToDto(beerOrder))
                .build());

        log.debug("Sent request to queue" + JmsConfig.ALLOCATE_ORDER_QUEUE + "for Beer Order Id: " + beerOrder.getId().toString());
    }
}
