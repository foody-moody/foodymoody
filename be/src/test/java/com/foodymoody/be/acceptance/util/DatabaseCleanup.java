package com.foodymoody.be.acceptance.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DatabaseCleanup {

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    private List<String> excludeTables = Arrays.asList("mood");

    public void setExcludeTables(List<String> excludeTables) {
        this.excludeTables = excludeTables;
        init();
    }

    @PostConstruct
    public void init() {
        tableNames = (List<String>) entityManager.createNativeQuery("SHOW TABLES").getResultList()
                .stream()
                .filter(tableNames -> !excludeTables.contains(tableNames))
                .collect(Collectors.toList());
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

}
