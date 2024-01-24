package com.foodymoody.be.member.application.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class MyFeedCollectionsResponse {

    private long count;
    private MyFeedCollectionAuthorResponse author;
    private Slice<MyFeedCollectionResponse> collections;

    public MyFeedCollectionsResponse(
            long count,
            MyFeedCollectionAuthorResponse authorSummary,
            Slice<MyFeedCollectionResponse> collectionSummaries) {
        this.count = count;
        this.author = authorSummary;
        this.collections = collectionSummaries;
    }

}
