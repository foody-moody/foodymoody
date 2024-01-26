package com.foodymoody.be.store;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.util.SqlFileExecutor;
import com.foodymoody.be.common.config.AppConfig;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.domain.StoreRepository;
import com.foodymoody.be.store.infra.StoreRepositoryImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({AppConfig.class, SqlFileExecutor.class, StoreRepositoryImpl.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class StoreRepositoryTest {

    @Autowired
    private StoreRepository repository;

    @Autowired
    private SqlFileExecutor sqlFileExecutor;

    @BeforeEach
    void setUp() {
        sqlFileExecutor.execute("data.sql");
    }

    @Test
    void when_find_details_by_id_if_success_then_return_details() {

        StoreId storeId = new StoreId("1");

        Optional<StoreDetailsResponse> findById = repository.fetchDetailsById(storeId);

        assertThat(findById.get().getName()).isEqualTo("영업중 식당");

    }

    @Test
    void when_find_details_by_id_if_not_exists_then_return_empty() {

        StoreId storeId = new StoreId("-1");

        Optional<StoreDetailsResponse> findById = repository.fetchDetailsById(storeId);

        assertThat(findById).isEmpty();

    }
}
