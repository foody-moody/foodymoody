package com.foodymoody.be.image.util;

import com.foodymoody.be.image.domain.Image;
import java.util.List;
import java.util.stream.Collectors;

public class ImageMapper {

    public static List<Image> toImage(List<String> imageUrls) {
        return imageUrls.stream()
                .map(Image::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
