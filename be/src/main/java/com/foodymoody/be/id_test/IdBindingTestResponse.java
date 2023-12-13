package com.foodymoody.be.id_test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodymoody.be.common.util.ids.BaseId;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdBindingTestResponse {

    @JsonProperty
    private MemberId memberId;
    @JsonProperty
    private FeedId feedId;
    @JsonProperty
    private CommentId commentId;
    @JsonProperty
    private BaseId pathId;
    @JsonProperty
    private BaseId annotationId;
    @JsonProperty
    private BaseId paramId;

}
