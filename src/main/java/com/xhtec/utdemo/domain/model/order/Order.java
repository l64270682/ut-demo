package com.xhtec.utdemo.domain.model.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 订单
 *
 * @author hhy@100fen.cn
 */
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String no;

    private long userId;

    private List<Goods> goods;

    private BigDecimal amount;

    private BigDecimal discount;

    private String token;

    private OrderStatus status;

    private Date createTime;
    private Date lastModifiedDate;

    public Order(long userId, String no, List<Goods> goods, BigDecimal amount, BigDecimal discount, String token, OrderStatus status) {
        this.userId = userId;
        this.no = no;
        this.goods = goods;
        this.amount = amount;
        this.discount = discount;
        this.token = token;
        this.status = status;

        this.createTime = new Date();
        this.lastModifiedDate = new Date();
    }

    public boolean pay(BigDecimal amount) {
        if (OrderStatus.UNPAID.equals(this.status)
                && amount.equals(amount)) {
            this.status = OrderStatus.PAID;
            this.lastModifiedDate = new Date();
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public long getUserId() {
        return userId;
    }

    public List<Goods> getGoods() {
        return Collections.unmodifiableList(goods);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

}
