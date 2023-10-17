package com.foodymoody.be.feed.mapper;

import com.foodymoody.be.feed.dto.FeedRegisterRequestMenu;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.util.MenuMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuMapperTest {

    @DisplayName("피드 등록 요청의 메뉴 목록을 메뉴 엔티티로 맵핑한다.")
    @Test
    void toMenu() {
        // given
        FeedRegisterRequestMenu MaratangRequest = new FeedRegisterRequestMenu("마라탕", 4);
        FeedRegisterRequestMenu TtoekBokkiRequest = new FeedRegisterRequestMenu("떡볶이", 5);
        List<FeedRegisterRequestMenu> menuRequest = List.of(MaratangRequest, TtoekBokkiRequest);

        // when
        List<Menu> actualMenus = MenuMapper.toMenu(menuRequest);

        // then
        // ctrl alt l
        Menu Maratang = new Menu("마라탕", 4);
        Menu TtoekBokki = new Menu("떡볶이", 5);
        List<Menu> expectedMenus = List.of(Maratang, TtoekBokki);
        org.assertj.core.api.Assertions.assertThat(actualMenus)
                .usingRecursiveComparison().isEqualTo(expectedMenus);
    }
}