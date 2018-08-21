package com.xhtec.utdemo.application;

import com.xhtec.utdemo.application.dto.AlipayPayResponse;
import com.xhtec.utdemo.domain.model.event.PayEventPublisher;
import com.xhtec.utdemo.domain.model.event.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付服务
 *
 * @author hhy@100fen.cn
 */
@Slf4j
@Service
public class PayService {

    @Autowired
    private PayEventPublisher payEventPublisher;

    public void receiveAlipayResponse(AlipayPayResponse request) {
        boolean succ = request.verify();
        if (succ) {
            String orderNo = request.getBizId();
            String amount = request.getAmount();

            OrderPaidEvent event = new OrderPaidEvent(orderNo, amount, "alipay", request.getTransactionId());
            payEventPublisher.publish(event);
        } else {
            log.warn("支付宝支付处理失败，验证失败 [transactionId={}\torderId={}]",
                    request.getTransactionId(), request.getBizId());
        }
    }

}
