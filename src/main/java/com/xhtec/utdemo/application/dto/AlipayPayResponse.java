package com.xhtec.utdemo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author hhy@100fen.cn
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlipayPayResponse {

    private String bizId;
    private String transactionId;

    private String amount;

    public boolean verify() {
        return StringUtils.isNotBlank(transactionId);
    }
}
