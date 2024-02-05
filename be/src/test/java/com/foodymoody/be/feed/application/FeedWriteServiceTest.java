package com.foodymoody.be.feed.application;

import com.foodymoody.be.feed.application.service.FeedWriteService;
import com.foodymoody.be.feed.infra.persistence.FeedRepositoryImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedWriteServiceTest {

    @InjectMocks
    private FeedWriteService feedWriteService;
    @Mock
    private FeedRepositoryImpl feedRepository;

    // TODO: feed 등록, 수정, 삭제의 조건(@Blank, @Size ...) 추가 후 그에 따른 테스트 작성

}
