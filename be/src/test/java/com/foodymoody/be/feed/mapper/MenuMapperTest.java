package com.foodymoody.be.feed.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.feed.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.util.MenuMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuMapperTest {

    @DisplayName("생성된 id와 메뉴를 성공적으로 매핑할 수 있다.")
    @Test
    void toOnlyMenu() {
        // given
        FeedRegisterRequestMenu 마라탕_Request = new FeedRegisterRequestMenu("마라탕", 4);
        String generatedId = "1";

        // when
        Menu actualMenu = MenuMapper.getMenu(generatedId, 마라탕_Request);

        // then
        Menu expectedMenu = new Menu(generatedId, "마라탕", 4);
        assertThat(actualMenu)
                .usingRecursiveComparison()
                .isEqualTo(expectedMenu);
    }

}
