package com.foodymoody.be.heart.repository;

import com.foodymoody.be.heart.domain.Heart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HeartRepository extends JpaRepository<Heart, String> {

    Optional<Heart> findByFeedId(String feedId);

    Optional<Heart> findHeartByMemberIdAndFeedId(String memberId, String feedId);

    boolean existsHeartByMemberIdAndFeedId(String memberId, String feedId);

    @Modifying
    @Query("UPDATE Heart _heart SET _heart.count = _heart.count + 1 WHERE _heart.feedId = :feedId")
    void incrementCount(String feedId);

}
