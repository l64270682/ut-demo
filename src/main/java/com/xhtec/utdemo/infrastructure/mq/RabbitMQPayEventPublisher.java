package com.xhtec.utdemo.infrastructure.mq;

import com.xhtec.utdemo.config.MQConfig;
import com.xhtec.utdemo.domain.model.event.OrderPaidEvent;
import com.xhtec.utdemo.domain.model.event.PayEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * @author hhy@100fen.cn
 */
@ConditionalOnMissingBean(PayEventPublisher.class)
@Component
@Slf4j
public class RabbitMQPayEventPublisher implements PayEventPublisher {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private MQConfig mqConfig;

    @Override
    public void publish(OrderPaidEvent event) {
        try {
            rabbitTemplate.convertAndSend(mqConfig.getPayEvents(), event);
        } catch (Exception e) {
            log.warn("支付事件投递失败 [orderNo={}\tpayType={}\ttransactionId={}]",
                    event.getOrderNo(),
                    event.getPayType(),
                    event.getTransactionId());

        }
    }
}
