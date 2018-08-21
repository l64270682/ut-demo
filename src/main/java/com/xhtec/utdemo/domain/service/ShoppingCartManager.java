package com.xhtec.utdemo.domain.service;

import com.xhtec.utdemo.domain.model.cart.CartGoods;
import com.xhtec.utdemo.domain.model.sku.SKU;
import com.xhtec.utdemo.domain.model.cart.ShoppingCart;
import com.xhtec.utdemo.domain.model.cart.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * shopping cart
 *
 * @author hhy@100fen.cn
 */
@Service
public class ShoppingCartManager {

    @Autowired
    private RedisTemplate objectRedisTemplate;

    public ShoppingCart getShoppingCart(long userId) {
        return new ShoppingCart(userId, new ShoppingCartRepositoryImpl());
    }

    private class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

        @Override
        public void save(long userId, Set<CartGoods> goods) {
            ValueOperations<String, Set<CartGoods>> operations = objectRedisTemplate.opsForValue();
            operations.set("cart_" + userId, goods);
        }

        @Override
        public Set<CartGoods> load(long userId) {
            ValueOperations<String, Set<CartGoods>> operations = objectRedisTemplate.opsForValue();
            return operations.get("cart_" + userId);
        }
    }

}
