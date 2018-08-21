package com.xhtec.utdemo.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;
import java.util.Set;

/**
 * 购物车
 *
 * @author hhy@100fen.cn
 */
@Data
public class CartDTO {

    private List<GoodsDTO> goods;

    protected CartDTO() {}

    public CartDTO(List<GoodsDTO> goods) {
        this.goods = goods;
    }
}
