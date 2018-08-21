package com.xhtec.utdemo.interfaces.http;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xhtec.utdemo.test.utils.BaseRestTest;
import com.xhtec.utdemo.application.dto.PayIssueRequest;
import com.xhtec.utdemo.application.dto.PayIssueResponse;
import com.xhtec.utdemo.domain.model.order.Goods;
import com.xhtec.utdemo.domain.model.order.Order;
import com.xhtec.utdemo.domain.model.order.OrderStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author hhy@100fen.cn
 */
public class PayControllerTests extends BaseRestTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    Order expectOrder;

    @Before
    public void setup() {
        Order order = new Order(1,
                UUID.randomUUID().toString(),
                Lists.newArrayList(new Goods(1, "铁三角头戴式耳机", BigDecimal.valueOf(1350), 1)),
                BigDecimal.valueOf(1350), BigDecimal.valueOf(0), UUID.randomUUID().toString(), OrderStatus.UNPAID);
        mongoTemplate.insert(order);

        expectOrder = order;
    }

    @After
    public void cleanup() {
        mongoTemplate.remove(expectOrder);
    }

    @Test
    public void testPayOrder() throws Exception {
        // 发起支付
        PayIssueRequest request = PayIssueRequest.builder()
                .payType("alipay")
                .bizId(expectOrder.getNo())
                .amount(expectOrder.getAmount().toPlainString())
                .build();

        PayIssueResponse response = restTemplate.postForObject("/api/v1/transactions/pay", request, PayIssueResponse.class);
        assertTrue(!response.getTransactionKey().isEmpty());

        // 模拟支付成功
        Map<String, String> payResponse = Maps.newHashMap();
        payResponse.put("bizId", request.getBizId());
        payResponse.put("transactionId", UUID.randomUUID().toString());
        payResponse.put("amount", request.getAmount());
        mockMvc.perform(post("/api/v1/transactions/alipay_callbck").contentType(JSON_TYPE).content(json(payResponse)))
                .andExpect(status().is2xxSuccessful());

        // 检查订单状态
        Order order = mongoTemplate.findOne(Query.query(Criteria.where("no").is(expectOrder.getNo())), Order.class);
        assertEquals(order.getStatus(), OrderStatus.PAID);
    }

}