import { useRef } from 'react';
import { useParams } from 'react-router-dom';
import { useGetComments, usePostComment } from 'service/queries/comment';
import { useFeedDetail } from 'service/queries/feed';
import { styled } from 'styled-components';
import { customScrollStyle } from 'styles/customStyle';
import { media } from 'styles/mediaQuery';
import { Badge } from 'components/common/badge/Badge';
import { Carousel } from 'components/common/carousel/Carousel';
import { CommentBox } from 'components/common/commentItem/CommentBox';
import { Dim } from 'components/common/dim/Dim';
import { FeedAction } from 'components/common/feedAction/FeedAction';
import { FeedUserInfo } from 'components/common/feedUserInfo/FeedUserInfo';
import { CommentInput } from 'components/common/input/CommentInput';
import { useModal } from 'components/common/modal/Modal';
import { useInput } from 'hooks/useInput';
import { useIntersectionObserver } from 'hooks/useObserver';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const DetailFeedModalPage = () => {
  // TODO 로딩 에러
  const { id: feedId } = useParams() as { id: string };
  const { data: feed } = useFeedDetail(feedId);
  const { comments, hasNextPage, fetchNextPage } = useGetComments(feedId);
  const { closeModal } = useModal<'commentAlert'>();
  const wrapperRef = useRef(null);
  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
    rootRef: wrapperRef,
  });
  const { mutate: commentMutate } = usePostComment(feedId);
  const { navigateToHome } = usePageNavigator();
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });
  console.log(feed);
  console.log('comment', comments);

  const handleSubmit = () => {
    isValid &&
      commentMutate({
        feedId: feed.id,
        content: value,
      });

    handleChange('');
  };

  return (
    <>
      <Dim
        onClick={() => {
          navigateToHome();
          closeModal('commentAlert');
        }}
      />
      <Wrapper ref={wrapperRef}>
        <Box>
          <Carousel images={feed.images} />

          <Content>
            <Info>
              <Detail>
                <FeedUserInfo // TODO 수정됨 요소 추가
                  member={feed?.member}
                  createdAt={
                    feed.createdAt === feed.updatedAt
                      ? feed.createdAt
                      : feed.updatedAt
                  }
                  location={feed?.location}
                  feedId={feed?.id}
                />
              </Detail>
              <Review>{feed?.review}</Review>
              <StoreMoodList>
                {feed?.storeMood.map(
                  // TOOD 무드 확인
                  (storeMood: { id: string; name: string }) => (
                    <Badge
                      variant="store"
                      badge={storeMood}
                      key={storeMood.id}
                    />
                  )
                )}
              </StoreMoodList>
            </Info>
            <FeedAction
              likeCount={feed?.likeCount}
              commentCount={feed?.commentCount}
            />
            <CommentContainer>
              <CommentInput
                value={value}
                limitedLength={200}
                onChangeValue={handleChange}
                onSubmitComment={handleSubmit}
              />
              <Comment>
                {comments?.map((comment) => (
                  <CommentBox // TODO const로 빼기
                    ref={comment === comments.length - 1 ? observeTarget : null}
                    key={comment.id}
                    createdAt={
                      comment.createdAt === comment.updatedAt
                        ? comment.createdAt
                        : comment.updatedAt
                    }
                    comment={comment}
                  />
                ))}
              </Comment>
            </CommentContainer>
          </Content>
        </Box>
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  overscroll-behavior: contain;
  padding: 16px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 100;
  max-width: 968px;
  min-width: 343px;
  width: 100%;

  ${media.md} {
    max-width: 580px;
    min-width: 379px;
    width: 100%;
    max-height: 88dvh;
    height: 100%;
    overflow: hidden;
  }
`;

const Review = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.black};
`;

const CommentContainer = styled.div`
  border-top: 1px solid ${({ theme: { colors } }) => colors.black};
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 16px;
`;

const Comment = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 24px;
  padding-bottom: 10px;
`;

const Info = styled.div`
  display: flex;
  flex-direction: column;
  padding: 16px;
  gap: 16px;
  border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};
`;

const StoreMoodList = styled.div`
  display: flex;
  gap: 8px;
`;

const Detail = styled.div`
  width: 100%;
`;

const Content = styled.div`
  display: flex;
  flex-direction: column;
  overflow: auto;
  overscroll-behavior: contain;

  ${customScrollStyle}

  ${media.md} {
    overflow: visible;
    max-height: 100%;
    height: 100%;
  }
`;

const Box = styled.div`
  border: 1px solid black;
  background-color: ${({ theme: { colors } }) => colors.white};
  display: flex;
  overflow: hidden;
  max-height: 968px;

  > div {
    max-width: 568px;
    max-height: 567px;
    width: 100%;
    border-right: 1px solid ${({ theme: { colors } }) => colors.black};
  }

  ${media.md} {
    flex-direction: column;
    max-width: 580px;
    width: 100%;
    max-height: 100%;
    overflow-y: auto;
    overscroll-behavior: contain;
    height: 100%;

    ${customScrollStyle}

    >div {
      border-right: none;
    }
  }
`;
