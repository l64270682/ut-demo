package com.xhtec.utdemo.application;

import com.google.common.base.Preconditions;
import com.xhtec.utdemo.application.assembler.ShoppingCartAssembler;
import com.xhtec.utdemo.application.dto.CartDTO;
import com.xhtec.utdemo.application.dto.GoodsDTO;
import com.xhtec.utdemo.domain.model.cart.ShoppingCart;
import com.xhtec.utdemo.domain.model.sku.SKU;
import com.xhtec.utdemo.domain.service.ShoppingCartManager;
import com.xhtec.utdemo.domain.service.SkuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车服务
 *
 * @author hhy@100fen.cn
 */
@Service
public class CartService {

    @Autowired
    private ShoppingCartManager shoppingCartManager;

    @Autowired
    private SkuManager skuManager;

    public CartDTO getCart(long userId) {
        final ShoppingCart car = shoppingCartManager.getShoppingCart(userId);

        return ShoppingCartAssembler.toGoods(car.getGoods());
    }

    /**
     * 添加到购物车
     *
     * @param userId
     * @param skuId
     * @param quantity
     */
    public void addToCart(long userId, long skuId, int quantity) {
        final SKU sku = skuManager.findSkuById(skuId);
        Preconditions.checkArgument(sku != null || !sku.isOff(), "该商品不存在或已下架");

        final ShoppingCart car = shoppingCartManager.getShoppingCart(userId);
        car.addToCart(sku, quantity);
    }

}
