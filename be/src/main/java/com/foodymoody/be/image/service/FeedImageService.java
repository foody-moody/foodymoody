package com.foodymoody.be.image.service;

import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.image.controller.ImageUploadResponse;
import com.foodymoody.be.image.domain.ImageCategory;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedImageService {

    private final ImageService imageService;
    private final FeedService feedService;

    public ImageUploadResponse save(ImageCategory category, String feedId, String memberId, MultipartFile file) {
        Feed feed = feedService.findFeed(feedId);
        validateIsAuthor(memberId, feed);
        return imageService.save(category, feedId, file);
    }

    private void validateIsAuthor(String memberId, Feed feed) {
        if (!Objects.equals(memberId, feed.getMemberId())) {
            throw new UnauthorizedException();
        }
    }

}
