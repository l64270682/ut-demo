package com.xhtec.utdemo.domain.model.cart;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.xhtec.utdemo.domain.model.sku.SKU;

import java.util.Collections;
import java.util.Set;

/**
 * 购物车
 *
 * @author hhy@100fen.cn
 */
public class ShoppingCart {

    private long userId;
    private Set<CartGoods> goods = Sets.newHashSet();
    private boolean cartLoaded;

    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart(long userId, ShoppingCartRepository shoppingCartRepository) {
        Preconditions.checkNotNull(shoppingCartRepository);
        this.userId = userId;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addToCart(SKU sku, int quantity) {
        Preconditions.checkArgument(sku != null);
        Preconditions.checkArgument(quantity > 0 && quantity <= (sku.getQuantity() - sku.getSold()),
                "库存不足");

        CartGoods existGoods = goods.stream().filter(g -> g.getSkuId() == sku.getId()).findFirst().orElse(null);
        if (existGoods != null) {
            existGoods.add(quantity);
        } else {
            existGoods = new CartGoods(sku.getId(), sku.getName(), quantity);
            this.goods.add(existGoods);
        }

        shoppingCartRepository.save(this.userId, this.goods);
    }

    protected void loadCartGoods() {
        if (!this.cartLoaded) {
            Set<CartGoods> cartGoods = shoppingCartRepository.load(userId);

            this.goods = Sets.newHashSet();

            if (cartGoods != null) {
                this.goods.addAll(cartGoods);
            }

            this.cartLoaded = true;
        }
    }

    public long getUserId() {
        return userId;
    }

    public Set<CartGoods> getGoods() {
        loadCartGoods();
        return Collections.unmodifiableSet(goods);
    }
}
