package com.xhtec.utdemo.interfaces.http;

import com.xhtec.utdemo.test.utils.BaseRestTest;
import com.xhtec.utdemo.application.dto.CartAddRequest;
import com.xhtec.utdemo.application.dto.CartDTO;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author hhy@100fen.cn
 */
@DirtiesContext
@Sql("/goods.sql")
public class ShoppingCartControllerTests extends BaseRestTest {

    @Test
    public void testAddGoodsToCart() throws Exception {
        // 购物车为空
        CartDTO cartDTO = restTemplate.getForObject("/api/v1/users/{userId}/carts/", CartDTO.class, 1L);
        assertTrue(cartDTO.getGoods().isEmpty());

        // 加入购物车
        CartAddRequest request = CartAddRequest.builder()
                .skuId(1L)
                .quantity(1)
                .build();
        mockMvc.perform(post("/api/v1/users/{userId}/carts/", 1L)
                .contentType(JSON_TYPE).content(json(request)))
                .andExpect(status().is2xxSuccessful());

        /*// 检查购物车
        cartDTO = restTemplate.getForObject("/api/v1/users/{userId}/carts/", CartDTO.class, 1L);
        assertTrue(cartDTO.getGoods().stream().anyMatch(goodsDTO ->
                goodsDTO.getSkuId() == 1L && goodsDTO.getQuantity() == 1));
    }*/

    }
}