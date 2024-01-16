package com.foodymoody.be.feed.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MenuId;
import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.menu.domain.entity.Menu;
import com.foodymoody.be.menu.application.MenuMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("메뉴 매퍼 테스트")
class MenuMapperTest {

    @DisplayName("생성된 id와 메뉴를 성공적으로 매핑할 수 있다.")
    @Test
    void toOnlyMenu() {
        // given
        FeedRegisterRequestMenu 마라탕_Request = new FeedRegisterRequestMenu("마라탕", 4);
        MenuId generatedId = IdFactory.createMenuId("1");

        // when
        Menu actualMenu = MenuMapper.makeMenu(generatedId, 마라탕_Request);

        // then
        Menu expectedMenu = new Menu(generatedId, "마라탕", 4);
        assertThat(actualMenu)
                .usingRecursiveComparison()
                .isEqualTo(expectedMenu);
    }

}
