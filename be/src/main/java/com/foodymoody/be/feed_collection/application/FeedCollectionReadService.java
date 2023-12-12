package com.foodymoody.be.feed_collection.application;

import com.foodymoody.be.feed_collection.domain.FeedCollectionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReadService {

    private final FeedCollectionDao dao;


}
