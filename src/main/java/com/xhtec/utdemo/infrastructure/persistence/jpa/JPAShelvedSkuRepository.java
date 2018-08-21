package com.xhtec.utdemo.infrastructure.persistence.jpa;

import com.xhtec.utdemo.domain.model.sku.ShelvedSku;
import com.xhtec.utdemo.domain.model.sku.ShelvedSkuRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hhy@100fen.cn
 */
@Repository
public interface JPAShelvedSkuRepository extends CrudRepository<ShelvedSku, Long>, ShelvedSkuRepository {
}
