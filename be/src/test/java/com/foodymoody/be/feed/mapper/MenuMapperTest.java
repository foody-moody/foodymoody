package com.foodymoody.be.feed.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.feed.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.util.MenuMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuMapperTest {

    @DisplayName("피드 등록 요청의 메뉴 목록을 메뉴 List 엔티티로 매핑한다.")
    @Test
    void toOnlyMenu() {
        // given
        FeedRegisterRequestMenu 마라탕_Request = new FeedRegisterRequestMenu("마라탕", 4);
        FeedRegisterRequestMenu 떡볶이_Request = new FeedRegisterRequestMenu("떡볶이", 5);

        // when
        List<Menu> actualMenus = MenuMapper.toOnlyMenu(List.of(마라탕_Request, 떡볶이_Request));

        // then
        List<Menu> expectedMenus = List.of(new Menu("마라탕", 4), new Menu("떡볶이", 5));
        assertThat(actualMenus)
                .usingRecursiveComparison()
                .isEqualTo(expectedMenus);
    }

    @DisplayName("이미지와 메뉴 목록을 메뉴 List 엔티티로 매핑한다.")
    @Test
    void toMenu() {
        // given
        ImageMenuPair 마라탕_Request = new ImageMenuPair("https://www.naver.com", new FeedRegisterRequestMenu("마라탕", 4));
        ImageMenuPair 떡볶이_Request = new ImageMenuPair("https://www.google.com", new FeedRegisterRequestMenu("떡볶이", 5));

        // when
        List<Menu> actualMenus = MenuMapper.toMenu(List.of(마라탕_Request, 떡볶이_Request));

        // then
        List<Menu> expectedMenus = List.of(new Menu("마라탕", 4), new Menu("떡볶이", 5));
        assertThat(actualMenus)
                .usingRecursiveComparison()
                .isEqualTo(expectedMenus);
    }

}
