package com.xhtec.utdemo.domain.model.sku;

/**
 * @author hhy@100fen.cn
 */
public interface SkuRepository {

    /**
     * 获取库存商品
     *
     * @param skuId
     * @return
     */
    SKU findById(long skuId);

}
