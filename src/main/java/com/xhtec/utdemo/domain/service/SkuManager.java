package com.xhtec.utdemo.domain.service;

import com.xhtec.utdemo.domain.model.sku.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * sku 管理
 * @author hhy@100fen.cn
 */
@Service
public class SkuManager {

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private SkuActionRepository skuActionRepository;

    public SKU findSkuById(long skuId) {
        return skuRepository.findById(skuId);
    }

    /**
     * 准备购买，扣减库存
     * @param sku
     * @param quantity
     * @param userId
     * @param token
     * @return
     */
    public boolean prepareOrderSku(SKU sku, int quantity, long userId, String token) {
        boolean succ = sku.reduce(quantity);

        // 记录扣减操作
        if (succ) {
            SkuAction action = new SkuAction(sku.getId(), userId, token, Action.REDUCE, quantity, System.currentTimeMillis());
            skuActionRepository.insert(action);
        }

        return succ;
    }
}
