package com.xhtec.utdemo.application.dto;

/**
 * @author hhy@100fen.cn
 */
public class OrderGoodsDTO {

    private Long skuId;

    private Integer quantity;

    public OrderGoodsDTO() {}

    public OrderGoodsDTO(Long skuId, Integer quantity) {
        this.skuId = skuId;
        this.quantity = quantity;
    }

    public Long getSkuId() {
        return skuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
