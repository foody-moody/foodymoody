package com.foodymoody.be.feed.application;

import static org.assertj.core.api.Assertions.*;

import com.foodymoody.be.common.exception.ErrorMessage;
import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed.domain.repository.FeedRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedWriteServiceTest {

    @InjectMocks
    private FeedWriteService feedWriteService;
    @Mock
    private FeedRepository feedRepository;

    // TODO: feed 등록, 수정, 삭제의 조건(@Blank, @Size ...) 추가 후 그에 따른 테스트 작성

}
