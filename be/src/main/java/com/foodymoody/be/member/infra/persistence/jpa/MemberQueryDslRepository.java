package com.foodymoody.be.member.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.MyFeedCollectionWithFeedIdsSummary;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MemberQueryDslRepository {

    MyFeedCollectionsResponse fetchMyCollectionResponse(MemberId id, MemberId currentMemberId, Pageable pageable);

    List<MyFeedCollectionWithFeedIdsSummary> fetchMyFeedCollectionWithFeedIds(MemberId currentMemberId);

}
