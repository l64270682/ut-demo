package com.xhtec.utdemo.domain.model.cart;

import com.xhtec.utdemo.domain.model.sku.SKU;

import java.util.Set;

/**
 * 购物车
 *
 * @author hhy@100fen.cn
 */
public interface ShoppingCartRepository {

    /**
     * 保存购物车
     *
     * @param userId
     * @param goods
     */
    void save(long userId, Set<CartGoods> goods);

    /**
     * 加载购物车
     *
     * @param userId
     * @return
     */
    Set<CartGoods> load(long userId);
}
