package com.xhtec.utdemo.domain.model.event;

/**
 * @author hhy@100fen.cn
 */
public class OrderPaidEvent {

    private String orderNo;

    private String amount;

    private String payType;

    private String transactionId;

    private long createdAt;

    public OrderPaidEvent(String orderNo, String amount, String payType, String transactionId) {
        this.orderNo = orderNo;
        this.amount = amount;
        this.payType = payType;
        this.transactionId = transactionId;

        this.createdAt = System.currentTimeMillis();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public String getPayType() {
        return payType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
