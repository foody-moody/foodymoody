package com.foodymoody.be.image.service;

import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.image.controller.ImageUploadResponse;
import com.foodymoody.be.image.domain.ImageCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedImageService {

    private final ImageService imageService;
    private final FeedReadService feedReadService;

    public ImageUploadResponse save(ImageCategory category, String feedId, String memberIdValue, MultipartFile file) {
        FeedId feedIdObj = new FeedId(feedId);
        MemberId memberId = new MemberId(memberIdValue);
        Feed feed = feedReadService.findFeed(feedIdObj);
        validateIsAuthor(memberId, feed);
        return imageService.save(category, feedId, file);
    }

    private void validateIsAuthor(MemberId memberId, Feed feed) {
        if (!feed.getMemberId().isSame(memberId)) {
            throw new UnauthorizedException();
        }
    }

}
