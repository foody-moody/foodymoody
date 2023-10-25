package com.foodymoody.be.image.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
