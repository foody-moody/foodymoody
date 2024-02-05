package com.foodymoody.be.menu.application.service;

import com.foodymoody.be.menu.domain.entity.Menu;
import com.foodymoody.be.menu.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

}
