package com.xhtec.utdemo.interfaces.http;

import com.xhtec.utdemo.test.utils.BaseRestTest;
import com.xhtec.utdemo.application.dto.SkuDTO;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertEquals;

/**
 * @author hhy@100fen.cn
 */
@Sql("/goods.sql")
public class ShelvesControllerTests extends BaseRestTest {

    @Test
    public void testGetShelveSkus() {
        final SkuDTO[] goods = restTemplate.getForObject("/api/v1/shelves/{shelveId}/",
                SkuDTO[].class, 1L);
        assertEquals(3, goods.length);
    }

}