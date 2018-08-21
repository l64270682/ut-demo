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
public class GoodsDTO {

    private Long skuId;

    private String name;

    private Integer quantity;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        GoodsDTO goodsDTO = (GoodsDTO) o;

        return skuId.equals(goodsDTO.skuId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + skuId.hashCode();
        return result;
    }
}
