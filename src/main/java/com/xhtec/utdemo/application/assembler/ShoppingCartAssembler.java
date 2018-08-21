package com.xhtec.utdemo.application.assembler;

import com.xhtec.utdemo.application.dto.CartDTO;
import com.xhtec.utdemo.application.dto.GoodsDTO;
import com.xhtec.utdemo.domain.model.cart.CartGoods;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hhy@100fen.cn
 */
public class ShoppingCartAssembler {

    public static CartDTO toGoods(Set<CartGoods> goods) {
        return new CartDTO(goods.stream()
                .map(cg -> GoodsDTO.builder().skuId(cg.getSkuId()).name(cg.getName()).quantity(cg.getQuantity()).build())
                .collect(Collectors.toList()));
    }

}
