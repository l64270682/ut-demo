package com.xhtec.utdemo.application.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author hhy@100fen.cn
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    private String token;

    @Singular
    private Set<OrderGoodsDTO> goods;

}
