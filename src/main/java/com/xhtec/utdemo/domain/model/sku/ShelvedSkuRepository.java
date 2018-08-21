package com.xhtec.utdemo.domain.model.sku;

import com.xhtec.utdemo.domain.model.Status;

import java.util.List;

/**
 * @author hhy@100fen.cn
 */
public interface ShelvedSkuRepository {

    List<ShelvedSku> findByShelveIdAndStatus(long shelveId, Status status);

}
