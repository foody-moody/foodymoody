package com.foodymoody.be.acceptance.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TableCleanup {

    @PersistenceContext
    private EntityManager entityManager;
    private String tableName;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
    }
}
