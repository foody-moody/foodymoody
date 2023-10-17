package com.foodymoody.be.feed.domain;

import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;

@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String review;
    private String mood;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus;

    public Feed() {
    }

    @Builder
    public Feed(String location, String review, String mood, List<Image> images, List<Menu> menus) {
        this.location = location;
        this.review = review;
        this.mood = mood;
        this.images = images;
        this.menus = menus;
    }

    public Long getId() {
        return id;
    }

}
