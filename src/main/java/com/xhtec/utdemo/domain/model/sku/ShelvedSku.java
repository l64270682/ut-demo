package com.xhtec.utdemo.domain.model.sku;

import com.xhtec.utdemo.domain.model.Status;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 货架商品
 *
 * @author hhy@100fen.cn
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ShelvedSku {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private long shelveId;

    private long skuId;

    private Status status;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    public ShelvedSku() {}

    public ShelvedSku(long shelveId, long skuId, Status status) {
        this.shelveId = shelveId;
        this.skuId = skuId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getShelveId() {
        return shelveId;
    }

    public long getSkuId() {
        return skuId;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShelvedSku that = (ShelvedSku) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
