package com.xhtec.utdemo.domain.service;

import com.xhtec.utdemo.domain.model.*;
import com.xhtec.utdemo.domain.model.sku.Shelve;
import com.xhtec.utdemo.domain.model.sku.ShelvedSku;
import com.xhtec.utdemo.domain.model.sku.ShelvedSkuRepository;
import com.xhtec.utdemo.domain.model.sku.ShelvesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 货架管理
 *
 * @author hhy@100fen.cn
 */
@Service
public class ShelvesManager {

    @Autowired
    private ShelvesRepository shelvesRepository;

    @Autowired
    private ShelvedSkuRepository shelvedSkuRepository;

    /**
     * 获取货架商品
     *
     * @param shelveId
     * @return
     */
    public Shelve findShelve(long shelveId) {
        Shelve shelve = shelvesRepository.findById(shelveId);
        if (shelve != null) {
            List<ShelvedSku> shelvedSkus = shelvedSkuRepository.findByShelveIdAndStatus(shelveId, Status.OK);

            shelve.acceptSkus(shelvedSkus);
        }
        return shelve;
    }

}
