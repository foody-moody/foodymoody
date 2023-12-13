package com.foodymoody.be.id_test;

import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdBindingTestRequest {

    private MemberId memberId;
    private FeedId feedId;
    private CommentId commentId;

    @Override
    public String toString() {
        return String.format(
                "BindingTestRequest{\n" +
                "memberId - value:%s type:%s\n" +
                ", feedId - value:%s type:%s\n" +
                ", commentId - value:%s type:%s\n" +
                "}\n",
                memberId.getValue(), memberId.getClass(),
                feedId.getValue(), feedId.getClass(),
                commentId.getValue(), commentId.getClass()
        );
    }
}
