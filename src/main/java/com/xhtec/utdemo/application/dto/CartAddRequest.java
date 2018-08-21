package com.xhtec.utdemo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hhy@100fen.cn
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartAddRequest {

    private Long skuId;

    private Integer quantity;

}
