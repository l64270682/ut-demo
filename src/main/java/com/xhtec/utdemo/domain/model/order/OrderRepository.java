package com.xhtec.utdemo.domain.model.order;

import java.util.List;

/**
 * @author hhy@100fen.cn
 */
public interface OrderRepository {

    Order save(Order entity);

    Order findByUserIdAndToken(long userId, String token);

    List<Order> findAllByUserId(long userId);

    Order findByNo(String orderNo);
}
