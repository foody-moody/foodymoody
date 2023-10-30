package com.foodymoody.be.feed.repository;

import com.foodymoody.be.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, String> {

}
