package com.xhtec.utdemo.infrastructure.persistence.jpa;

import com.xhtec.utdemo.domain.model.sku.Shelve;
import com.xhtec.utdemo.domain.model.sku.ShelvesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hhy@100fen.cn
 */
@Repository
public interface JPAShelvesRepository extends CrudRepository<Shelve, Long>, ShelvesRepository {
}
