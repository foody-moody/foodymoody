package com.foodymoody.be.image.domain;

import com.foodymoody.be.common.util.ids.BaseId;
import java.util.function.Function;

public enum ImageCategory {

    MEMBER(id -> String.format("members/%s", id.getValue())),
    FEED(id -> "feeds");

    private final Function<BaseId, String> prefixBuilder;

    ImageCategory(Function<BaseId, String> prefixBuilder) {
        this.prefixBuilder = prefixBuilder;
    }

    public String getPrefix(BaseId source) {
        return prefixBuilder.apply(source);
    }
}
