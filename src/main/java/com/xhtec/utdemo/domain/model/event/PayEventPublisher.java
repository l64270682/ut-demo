package com.xhtec.utdemo.domain.model.event;

/**
 * @author hhy@100fen.cn
 */
public interface PayEventPublisher {

    /**
     * 发布事件
     *
     * @param event
     */
    void publish(OrderPaidEvent event);

}
