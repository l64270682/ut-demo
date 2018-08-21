package com.xhtec.utdemo.domain.model.sku;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * 货架
 *
 * @author hhy@100fen.cn
 */
@Entity
public class Shelve {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String name;

    @Transient
    private List<ShelvedSku> skus;

    protected Shelve() {}

    public Shelve(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void acceptSkus(List<ShelvedSku> shelvedSkus) {
        this.skus = shelvedSkus;
    }

    public List<ShelvedSku> getSkus() {
        if (skus != null) {
            return Collections.unmodifiableList(skus);
        }
        return Collections.emptyList();
    }

}
