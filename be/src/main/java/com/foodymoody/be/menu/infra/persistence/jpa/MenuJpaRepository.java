package com.foodymoody.be.menu.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.MenuId;
import com.foodymoody.be.menu.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuJpaRepository extends JpaRepository<Menu, MenuId> {

}
