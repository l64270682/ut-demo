package com.xhtec.utdemo.infrastructure.persistence.jpa;

import com.xhtec.utdemo.domain.model.sku.SKU;
import com.xhtec.utdemo.domain.model.sku.SkuRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hhy@100fen.cn
 */
@Repository
public interface JPASkuRepository extends CrudRepository<SKU, Long>, SkuRepository {
}
