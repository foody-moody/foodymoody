package com.foodymoody.be.image.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}
