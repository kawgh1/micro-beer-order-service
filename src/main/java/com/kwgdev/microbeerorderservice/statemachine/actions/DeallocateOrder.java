package com.kwgdev.microbeerorderservice.statemachine.actions;

/**
 * created by kw on 1/14/2021 @ 7:24 PM
 */
import com.kwgdev.microbeerorderservice.config.JmsConfig;
import com.kwgdev.microbeerorderservice.domain.BeerOrder;
import com.kwgdev.microbeerorderservice.domain.BeerOrderEventEnum;
import com.kwgdev.microbeerorderservice.domain.BeerOrderStatusEnum;
import com.kwgdev.microbeerorderservice.web.mappers.BeerOrderMapper;
import com.kwgdev.model.events.DeAllocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import static com.kwgdev.microbeerorderservice.services.BeerOrderManagerImpl.ORDER_OBJECT_HEADER;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocateOrder implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {
    private final JmsTemplate jmsTemplate;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        BeerOrder beerOrder = context.getStateMachine().getExtendedState()
                .get(ORDER_OBJECT_HEADER, BeerOrder.class);


        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE, DeAllocateOrderRequest
                .builder()
                .beerOrder(beerOrderMapper.beerOrderToDto(beerOrder))
                .build());

        log.debug("Sent request to queue" + JmsConfig.DEALLOCATE_ORDER_QUEUE + "for Beer Order Id: " + beerOrder.getId().toString());
    }
}