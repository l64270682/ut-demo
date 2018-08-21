package com.xhtec.utdemo.application.dto;

import com.xhtec.utdemo.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 商品
 *
 * @author hhy@100fen.cn
 */
@Data
@AllArgsConstructor
public class SkuDTO {

    private long id;

    private String name;

    private String price;

    private int quantity;

    private int sold;

}
