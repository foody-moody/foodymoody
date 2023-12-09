package com.foodymoody.be.image.domain;

import java.util.function.UnaryOperator;

public enum ImageCategory {

    MEMBER(id -> String.format("members/%s", id)),
    FEED(id -> "feeds");

    private final UnaryOperator<String> prefixBuilder;

    ImageCategory(UnaryOperator<String> prefixBuilder) {
        this.prefixBuilder = prefixBuilder;
    }

    public String getPrefix(String source) {
        return prefixBuilder.apply(source);
    }
}
