package com.foodymoody.be.image.util;

import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.image.domain.Image;
import java.util.List;
import java.util.stream.Collectors;

public class ImageMapper {

    public static List<Image> toImage(List<ImageMenuPair> imageMenuPairs) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> new Image(imageMenuPair.getImageUrl()))
                .collect(Collectors.toUnmodifiableList());
    }

}
