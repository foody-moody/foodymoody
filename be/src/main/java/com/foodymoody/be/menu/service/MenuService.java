package com.foodymoody.be.menu.service;

import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu findBy(String menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

}
