package com.xhtec.utdemo.domain.model.cart;

import static java.lang.Integer.max;

/**
 * @author hhy@100fen.cn
 */
public class CartGoods {

    private long skuId;

    private String name;

    private int quantity;


    protected CartGoods() {
    }

    public CartGoods(long skuId, String name, int quantity) {
        this.skuId = skuId;
        this.name = name;
        this.quantity = quantity;
    }

    public void add(int quantity) {
        this.quantity += quantity;
    }

    public boolean remove(int quantity) {
        this.quantity = max(this.quantity - quantity, 0);
        return this.quantity == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CartGoods cartGoods = (CartGoods) o;

        return skuId == cartGoods.skuId;
    }

    @Override
    public int hashCode() {
        return (int) (skuId ^ (skuId >>> 32));
    }

    public long getSkuId() {
        return skuId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

}
