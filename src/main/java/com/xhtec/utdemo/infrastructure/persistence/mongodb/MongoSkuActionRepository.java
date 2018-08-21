package com.xhtec.utdemo.infrastructure.persistence.mongodb;

import com.xhtec.utdemo.domain.model.sku.SkuAction;
import com.xhtec.utdemo.domain.model.sku.SkuActionRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hhy@100fen.cn
 */
@Repository
public interface MongoSkuActionRepository extends MongoRepository<SkuAction, String>,SkuActionRepository {
}
