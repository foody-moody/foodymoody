package com.foodymoody.be.menu.repository;

import com.foodymoody.be.common.util.ids.MenuId;
import com.foodymoody.be.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, MenuId> {

}
