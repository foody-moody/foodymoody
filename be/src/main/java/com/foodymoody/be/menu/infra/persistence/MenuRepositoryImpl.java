package com.foodymoody.be.menu.infra.persistence;

import com.foodymoody.be.menu.domain.entity.Menu;
import com.foodymoody.be.menu.domain.repository.MenuRepository;
import com.foodymoody.be.menu.infra.persistence.jpa.MenuJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MenuRepositoryImpl implements MenuRepository {

    private final MenuJpaRepository menuJpaRepository;

    @Override
    public Menu save(Menu menu) {
        return menuJpaRepository.save(menu);
    }

}
