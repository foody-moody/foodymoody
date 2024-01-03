import { Suspense, useRef } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import { usePostComment } from 'service/queries/comment';
import { useFeedDetail } from 'service/queries/feed';
import { styled } from 'styled-components';
import { customScrollStyle } from 'styles/customStyle';
import { media } from 'styles/mediaQuery';
import { CommentList } from 'components/comment/CommentList';
import { CommentInput } from 'components/commentInput/CommentInput';
import { Badge } from 'components/common/badge/Badge';
import { Carousel } from 'components/common/carousel/Carousel';
import { Dim } from 'components/common/dim/Dim';
import { FeedAction } from 'components/common/feedAction/FeedAction';
import { FeedUserInfo } from 'components/common/feedUserInfo/FeedUserInfo';
import { useModal } from 'components/common/modal/useModal';
import { CommentSkeleton } from 'components/common/skeleton/CommentSkeleton';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { useInput } from 'hooks/useInput';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const DetailFeedModalPage = () => {
  // TODO 로딩 에러
  const location = useLocation();
  const background = location.state && location.state.background;

  const { id: feedId } = useParams() as { id: string };
  const { data: feed } = useFeedDetail(feedId);

  const { closeModal } = useModal<'commentAlert'>();
  const wrapperRef = useRef<HTMLDivElement>(null);

  const { mutate: commentMutate } = usePostComment();
  const { navigateToBack, navigateToHome } = usePageNavigator();
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });

  const handleNavigateToBack = () => {
    if (background === 'detailFeed') {
      navigateToBack();
      closeModal('commentAlert');
    } else {
      navigateToHome();
    }
  };

  const handleSubmit = () => {
    isValid &&
      commentMutate({
        feedId: feed.id,
        content: value,
      });

    handleChange('');
  };

  const isUpdated = feed?.createdAt !== feed?.updatedAt;

  return (
    <>
      {/* 로딩, 에러 추가 */}
      <Dim
        onClick={() => {
          handleNavigateToBack();
        }}
      />
      <Wrapper ref={wrapperRef}>
        {feed && (
          <Box>
            <Carousel images={feed?.images} />
            <Content>
              <Info>
                <Detail>
                  <FeedUserInfo // TODO 수정됨 요소 추가
                    feedId={feed?.id}
                    member={feed?.member}
                    createdAt={isUpdated ? feed.updatedAt : feed.createdAt}
                    isUpdated={isUpdated}
                    location={feed?.location}
                    thumbnail={feed.images[0]?.image.url}
                  />
                </Detail>
                <Review>{feed?.review}</Review>
                <StoreMoodList>
                  {feed?.storeMood.map(
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
                feedId={feed?.id}
                isLiked={feed?.liked}
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

                <Suspense
                  fallback={
                    <DeferredComponent>
                      <CommentSkeleton itemCount={10} />
                    </DeferredComponent>
                  }
                >
                  <CommentList feedId={feedId} rootRef={wrapperRef} />
                </Suspense>
              </CommentContainer>
            </Content>
          </Box>
        )}
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
