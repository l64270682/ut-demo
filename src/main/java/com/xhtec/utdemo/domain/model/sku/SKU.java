package com.xhtec.utdemo.domain.model.sku;

import com.xhtec.utdemo.domain.model.Status;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author hhy@100fen.cn
 */
@Entity
public class SKU {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private int quantity;

    private int sold;

    private Status status;

    protected SKU() {}

    public SKU(String name, BigDecimal price, int quantity, int sold, Status status) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sold = sold;
        this.status = status;
    }

    public boolean reduce(Integer quantity) {
        int left = this.quantity - this.sold;
        if (left > quantity) {
            this.sold += quantity;
            return true;
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSold() {
        return sold;
    }

    public Status getStatus() {
        return status;
    }

    @Transient
    public boolean isOff() {
        return !Status.OK.equals(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SKU sku = (SKU) o;

        return id == sku.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
