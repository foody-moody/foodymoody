package com.foodymoody.be.image.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Image {

    @Id
    private String id;
    private String url;

    public Image(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public Image() {
    }

    public static Image of(String id, String url) {
        return new Image(id, url);
    }

}
