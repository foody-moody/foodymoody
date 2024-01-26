package com.foodymoody.be.menu.domain.entity;

import com.foodymoody.be.common.util.ids.MenuId;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Menu {

    @Id
    @Getter
    private MenuId id;

    @Getter
    private String name;

    @Getter
    private int rating;

    public Menu(
            MenuId id,
            String name,
            int rating
    ) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

}
