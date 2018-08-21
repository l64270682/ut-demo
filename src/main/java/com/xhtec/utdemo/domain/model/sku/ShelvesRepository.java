package com.xhtec.utdemo.domain.model.sku;

/**
 * 货架库
 *
 * @author hhy@100fen.cn
 */
public interface ShelvesRepository {

    /**
     * 查找货架
     *
     * @param shelveId
     * @return
     */
    Shelve findById(long shelveId);

}
