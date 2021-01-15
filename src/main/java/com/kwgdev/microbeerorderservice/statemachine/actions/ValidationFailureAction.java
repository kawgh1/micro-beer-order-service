package com.kwgdev.microbeerorderservice.statemachine.actions;

/**
 * created by kw on 1/14/2021 @ 7:24 PM
 */
import com.kwgdev.microbeerorderservice.domain.BeerOrderEventEnum;
import com.kwgdev.microbeerorderservice.domain.BeerOrderStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 2/25/20.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {
    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        log.error("Validation Failed");
    }
}