package com.foodymoody.be.heart.repository;

import com.foodymoody.be.heart.domain.Heart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, String> {

    Optional<Heart> findHeartByMemberIdAndFeedId(String memberId, String feedId);

}
