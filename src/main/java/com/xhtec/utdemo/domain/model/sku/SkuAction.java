package com.xhtec.utdemo.domain.model.sku;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author hhy@100fen.cn
 */
@Document(collection = "sku_prepare_logs")
public class SkuAction {

    @Id
    private String id;

    private long skuId;

    private long userId;

    private String token;

    private Action action;

    private int quantity;

    private long timestamp;

    public SkuAction(long skuId, long userId, String token, Action action, int quantity, long timestamp) {
        this.skuId = skuId;
        this.userId = userId;
        this.token = token;
        this.action = action;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public long getSkuId() {
        return skuId;
    }

    public long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public Action getAction() {
        return action;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
