package com.kwgdev.microbeerorderservice.statemachine;

/**
 * created by kw on 1/14/2021 @ 7:23 PM
 */
import com.kwgdev.microbeerorderservice.domain.BeerOrder;
import com.kwgdev.microbeerorderservice.domain.BeerOrderEventEnum;
import com.kwgdev.microbeerorderservice.domain.BeerOrderStatusEnum;
import com.kwgdev.microbeerorderservice.repositories.BeerOrderRepository;
import com.kwgdev.microbeerorderservice.services.BeerOrderManagerImpl;
import com.kwgdev.microbeerorderservice.web.mappers.DateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt on 2019-09-08.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderStateChangeInterceptor extends StateMachineInterceptorAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final RestTemplateBuilder restTemplateBuilder;
    private final DateMapper dateMapper = new DateMapper();

    @Transactional
    @Override
    public void preStateChange(State<BeerOrderStatusEnum, BeerOrderEventEnum> state, Message<BeerOrderEventEnum> message, Transition<BeerOrderStatusEnum, BeerOrderEventEnum> transition, StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachine) {
        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders().getOrDefault(BeerOrderManagerImpl.ORDER_ID_HEADER, " ")))
                .ifPresent(orderId -> {
                    log.debug("Saving state for order id: " + orderId + " Status: " + state.getId());

                    BeerOrder beerOrder = beerOrderRepository.getOne(UUID.fromString(orderId));
                    beerOrder.setOrderStatus(state.getId());
                    beerOrderRepository.saveAndFlush(beerOrder);
                });
    }

//    @Override
//    public void postStateChange(State<BeerOrderStatusEnum, BeerOrderEventEnum> state, Message<BeerOrderEventEnum> message, Transition<BeerOrderStatusEnum, BeerOrderEventEnum> transition, StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachine) {
//        log.debug("Post State Change");
//
//        BeerOrder beerOrder = stateMachine.getExtendedState()
//                .get(ORDER_OBJECT_HEADER, BeerOrder.class);
//
//        try{
//            if (beerOrder.getOrderStatusCallbackUrl() != null) {
//
//                OrderStatusUpdate update = OrderStatusUpdate.builder()
//                        .id(beerOrder.getId())
//                        .orderId(beerOrder.getId())
//                        .version(beerOrder.getVersion() != null ? beerOrder.getVersion().intValue() : null)
//                        .createdDate(dateMapper.asOffsetDateTime(beerOrder.getCreatedDate()))
//                        .lastModifiedDate(dateMapper.asOffsetDateTime(beerOrder.getLastModifiedDate()))
//                        .orderStatus(beerOrder.getOrderStatus() != null ? beerOrder.getOrderStatus().toString() : null)
//                        .customerRef(beerOrder.getCustomerRef())
//                        .build();
//
//                log.debug("Posting to callback url");
//                RestTemplate restTemplate = restTemplateBuilder.build();
//                restTemplate.postForObject(beerOrder.getOrderStatusCallbackUrl(), update, String.class);
//            }
//        } catch (Throwable t){
//            log.error("Error Preforming callback for order: " + beerOrder.getId(), t);
//        }
//
//        log.debug("Post State change complete");
//    }
}