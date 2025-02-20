import { Suspense, useRef } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import { useToast } from 'recoil/toast/useToast';
import { usePostComment } from 'service/queries/comment';
import { useFeedDetail } from 'service/queries/feed';
import { styled } from 'styled-components';
import { customScrollStyle } from 'styles/customStyle';
import { media } from 'styles/mediaQuery';
import { CommentList } from 'components/comment/CommentList';
import { CommentInput } from 'components/commentInput/CommentInput';
import { StoreMoodBadge } from 'components/common/badge/StoreMoodBadge';
import { Carousel } from 'components/common/carousel/Carousel';
import { Dim } from 'components/common/dim/Dim';
import { FeedAction } from 'components/common/feedAction/FeedAction';
import { FeedUserInfo } from 'components/common/feedUserInfo/FeedUserInfo';
import { CustomHelmet } from 'components/common/helmet/Helmet';
import { useModal } from 'components/common/modal/useModal';
import { CommentSkeleton } from 'components/common/skeleton/CommentSkeleton';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { useAuthState } from 'hooks/auth/useAuth';
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

  const { mutate: commentMutate } = usePostComment(feed?.id);
  const toast = useToast();
  const { isLogin } = useAuthState();
  const { navigateToBack, navigateToHome, navigateToLogin } =
    usePageNavigator();
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });

  const handleNavigateToBack = () => {
    if (
      background === 'detailFeed' ||
      background === 'notiDetailFeed' ||
      background === 'profileDetailFeed'
    ) {
      navigateToBack();
      closeModal('commentAlert');
    } else {
      navigateToHome();
    }
  };

  const handleSubmit = () => {
    if (isLogin && isValid) {
      commentMutate({
        feedId: feed?.id,
        content: value,
      });
      handleChange('');
    } else {
      toast.noti('로그인이 필요한 서비스입니다.');
      navigateToLogin();
    }
  };

  return (
    <>
      {feed && (
        <CustomHelmet
          title={`${feed?.store?.name} 리뷰`}
          meta={{
            description: feed?.review,
            keywords: `${feed?.store?.name}, 맛집, 리뷰, 후기, 푸디무디, 푸디모디, 포디모디, 포디무디, foodymoody, 음식, 식당, 맛집추천, 맛집리뷰, 맛집탐방, 맛집투어, 내돈내산, 진짜, 존맛, 위치, ${feed?.store?.name} 위치, 주차, ${feed?.store?.name} 예약`,
            ogTitle: `${feed?.store?.name} 리뷰`,
            ogDescription: `${feed?.review} '진짜'만 모은 맛잘알들의 맛집 컬렉션! 푸디무디에서 ${feed?.member.nickname}님의 맛집 리뷰를 확인하세요!`,
            ogImage: feed?.images[0]?.image.url,
            ogUrl: `https://foodymoody.store/detail/feed/${feed?.id}`,
            ogType: 'website',
            ogSiteName: '푸디무디',
            ogLocale: 'ko_KR',
            twitterTitle: `푸디무디 - ${feed?.store?.name} 리뷰`,
            twitterDescription: feed?.review,
            twitterImage: feed?.images[0]?.image.url,
            twitterUrl: `https://foodymoody.store/detail/feed/${feed?.id}`,
            canonical: `https://foodymoody.store/detail/feed/${feed?.id}`,
          }}
        />
      )}

      <Dim
        onClick={() => {
          handleNavigateToBack();
        }}
      />
      <Wrapper ref={wrapperRef}>
        {feed && (
          <Box>
            <Menus>
              <Carousel images={feed?.images} />
            </Menus>
            <Content>
              <Info>
                <Detail>
                  <FeedUserInfo
                    feedId={feed?.id}
                    feedDescription={feed?.review}
                    member={feed?.member}
                    createdAt={feed?.updatedAt || feed?.createdAt}
                    isUpdated={feed?.updatedAt}
                    store={feed?.store}
                    thumbnail={feed?.images[0]?.image.url}
                  />
                </Detail>
                <Review>{feed?.review}</Review>
                <StoreMoodList>
                  {feed?.storeMood.map((storeMood: Badge) => (
                    <StoreMoodBadge name={storeMood.name} key={storeMood.id} />
                  ))}
                </StoreMoodList>
              </Info>
              <FeedAction
                feedId={feed?.id}
                isLiked={feed?.isLiked}
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

const Menus = styled.div`
  display: flex;
  align-items: center;
  background-color: ${({ theme: { colors } }) => colors.black};
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
