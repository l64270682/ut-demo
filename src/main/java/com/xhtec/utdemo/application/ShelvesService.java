package com.xhtec.utdemo.application;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.xhtec.utdemo.application.assembler.SkuAssembler;
import com.xhtec.utdemo.application.dto.SkuDTO;
import com.xhtec.utdemo.domain.model.*;
import com.xhtec.utdemo.domain.model.sku.SKU;
import com.xhtec.utdemo.domain.model.sku.Shelve;
import com.xhtec.utdemo.domain.model.sku.ShelvedSku;
import com.xhtec.utdemo.domain.service.ShelvesManager;
import com.xhtec.utdemo.domain.service.SkuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品货架服务
 *
 * @author hhy@100fen.cn
 */
@Service
public class ShelvesService {

    @Autowired
    private ShelvesManager shelvesManager;

    @Autowired
    private SkuManager skuManager;

    public List<SkuDTO> fetchShelveSkus(long shelveId) {
        Shelve shelve = shelvesManager.findShelve(shelveId);
        Preconditions.checkArgument(shelve != null, "该货架不存在");

        // 查库存
        final List<SKU> skus = Lists.newArrayList();
        for (ShelvedSku shelvedSku : shelve.getSkus()) {
            final SKU sku = skuManager.findSkuById(shelvedSku.getSkuId());
            if (sku != null && Status.OK.equals(sku.getStatus())) {
                skus.add(sku);
            }
        }

        return SkuAssembler.toDTOs(skus);
    }

}
