package com.xhtec.utdemo.interfaces.http;

import com.xhtec.utdemo.test.utils.BaseRestTest;
import com.xhtec.utdemo.application.dto.OrderCreateRequest;
import com.xhtec.utdemo.application.dto.OrderCreateResponse;
import com.xhtec.utdemo.application.dto.OrderDTO;
import com.xhtec.utdemo.application.dto.OrderGoodsDTO;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * @author hhy@100fen.cn
 */
@DirtiesContext
@Sql("/goods.sql")
public class OrderControllerTests extends BaseRestTest {

    @Test
    public void testCreateOrder() throws Exception {
        // 下单
        OrderCreateRequest orderRequest = OrderCreateRequest.builder()
                .token(UUID.randomUUID().toString())
                .good(new OrderGoodsDTO(1L, 1))
                .build();
        OrderCreateResponse orderResponse = restTemplate.postForObject("/api/v1/users/{userId}/orders/", orderRequest, OrderCreateResponse.class,
                1L);
        assertTrue(!orderResponse.getOrderNo().isEmpty());

        // 检查订单
        OrderDTO[] orders = restTemplate.getForObject("/api/v1/users/{userId}/orders/", OrderDTO[].class, 1L);
        assertTrue(Stream.of(orders).anyMatch(orderDTO -> orderDTO.getNo().equals(orderResponse.getOrderNo())));
    }

}