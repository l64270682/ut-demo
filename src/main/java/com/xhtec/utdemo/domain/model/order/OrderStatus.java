package com.xhtec.utdemo.domain.model.order;

/**
 * @author hhy@100fen.cn
 */
public enum  OrderStatus {

    /**
     * 待支付
     */
    UNPAID,

    /**
     * 已支付
     */
    PAID,

    /**
     * 已发货
     */
    DELIVERING,

    /**
     * 已取消
     */
    CANCELED
}
