package com.xhtec.utdemo.infrastructure.mq;

import com.xhtec.utdemo.domain.model.event.OrderPaidEvent;
import com.xhtec.utdemo.domain.model.event.PayEventPublisher;
import com.xhtec.utdemo.domain.service.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventBusPayEventPublisher implements PayEventPublisher {

    @Autowired
    private OrderManager orderManager;

    @Override
    public void publish(OrderPaidEvent event) {
        orderManager.didOrderPaid(event);
    }
}