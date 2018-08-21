package com.xhtec.utdemo.domain.model.order;

import java.math.BigDecimal;

/**
 * @author hhy@100fen.cn
 */
public class Goods {

    private long skuId;

    private String skuName;

    private BigDecimal price;

    private int quantity;

    private BigDecimal subTotal;

    public Goods() {
    }

    public Goods(long skuId, String skuName, BigDecimal price, int quantity) {
        this.skuId = skuId;
        this.skuName = skuName;
        this.price = price;
        this.quantity = quantity;
        this.subTotal = price.multiply(BigDecimal.valueOf(quantity));
    }

    public long getSkuId() {
        return skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
}
