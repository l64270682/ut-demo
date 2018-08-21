package com.xhtec.utdemo.application.dto;

import com.xhtec.utdemo.domain.model.order.Goods;
import com.xhtec.utdemo.domain.model.order.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author hhy@100fen.cn
 */
@Data
public class OrderDTO {

    private String no;

    private long userId;

    private List<Goods> goods;

    private BigDecimal amount;

    private BigDecimal discount;

    private String token;

    private OrderStatus status;

    private Date createTime;
    private Date lastModifiedDate;

}
