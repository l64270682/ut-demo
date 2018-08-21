package com.xhtec.utdemo.domain.service;

import com.xhtec.utdemo.domain.model.event.OrderPaidEvent;
import com.xhtec.utdemo.domain.model.order.Order;
import com.xhtec.utdemo.domain.model.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author hhy@100fen.cn
 */
@Component
@Slf4j
public class OrderManager {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private String redis;
    @RabbitListener
    public void didOrderPaid(OrderPaidEvent event) {
        String orderNo = event.getOrderNo();

        Order order = orderRepository.findByNo(orderNo);
        if (order == null) {
            log.error("订单支付处理失败，找不到该订单 [orderNo={}\tpayType={}\ttransactionId={}]", orderNo,
                    event.getPayType(), event.getTransactionId());
            return;
        }

        BigDecimal amount = new BigDecimal(event.getAmount());
        boolean succ = order.pay(amount);
        if (!succ) {
            log.error("订单支付处理失败，金额不对 [orderNo={}\tpayType={}\ttransactionId={}\tpaidAmount={}\trequireAmount={}]",
                    orderNo,
                    event.getPayType(), event.getTransactionId(),
                    amount, order.getAmount());
            return;
        }

        if (log.isInfoEnabled()) {
            log.info("订单支付处理成功 [orderNo={}\tpayType={}\ttransactionId={}]", orderNo,
                    event.getPayType(), event.getTransactionId());
        }

        orderRepository.save(order);
    }

}
