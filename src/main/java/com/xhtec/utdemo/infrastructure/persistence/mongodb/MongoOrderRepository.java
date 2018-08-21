package com.xhtec.utdemo.infrastructure.persistence.mongodb;

import com.xhtec.utdemo.domain.model.order.Order;
import com.xhtec.utdemo.domain.model.order.OrderRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hhy@100fen.cn
 */
@Repository
public interface MongoOrderRepository extends MongoRepository<Order, String>, OrderRepository {
}
