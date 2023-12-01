package com.foodymoody.be.heart.repository;

import com.foodymoody.be.heart.domain.Heart;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface HeartRepository extends JpaRepository<Heart, String> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<Heart> findHeartByMemberIdAndFeedId(String memberId, String feedId);

}
