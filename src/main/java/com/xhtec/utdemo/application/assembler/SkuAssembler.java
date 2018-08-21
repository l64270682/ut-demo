package com.xhtec.utdemo.application.assembler;

import com.xhtec.utdemo.application.dto.SkuDTO;
import com.xhtec.utdemo.domain.model.sku.SKU;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hhy@100fen.cn
 */
public class SkuAssembler {

    public static List<SkuDTO> toDTOs(List<SKU> skus) {
        return skus.stream()
                .map(sku -> new SkuDTO(sku.getId(), sku.getName(), sku.getPrice().toPlainString(), sku.getQuantity(), sku.getSold()))
                .collect(Collectors.toList());
    }
}
