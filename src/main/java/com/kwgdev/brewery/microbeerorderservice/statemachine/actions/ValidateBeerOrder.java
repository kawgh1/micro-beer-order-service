package com.kwgdev.brewery.microbeerorderservice.statemachine.actions;

/**
 * created by kw on 1/14/2021 @ 7:24 PM
 */
import com.kwgdev.brewery.microbeerorderservice.config.JmsConfig;
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrder;
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrderEventEnum;
import com.kwgdev.brewery.microbeerorderservice.domain.BeerOrderStatusEnum;
import com.kwgdev.brewery.microbeerorderservice.services.BeerOrderManagerImpl;
import com.kwgdev.brewery.microbeerorderservice.web.mappers.BeerOrderMapper;
import com.kwgdev.brewery.model.events.ValidateBeerOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * Validate Beer Order with Beer Service
 *
 * Created by jt on 2019-09-07.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateBeerOrder implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        BeerOrder beerOrder = stateContext.getStateMachine().getExtendedState()
                .get(BeerOrderManagerImpl.ORDER_OBJECT_HEADER, BeerOrder.class);

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE, ValidateBeerOrderRequest
                .builder()
                .beerOrder(beerOrderMapper.beerOrderToDto(beerOrder))
                .build());

        log.debug("Sent request to queue" + JmsConfig.VALIDATE_ORDER_QUEUE + "for Beer Order Id: " + beerOrder.getId().toString());
    }
}
