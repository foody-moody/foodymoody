import { useRef } from 'react';
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
  const { closeModal } = useModal<'commentAlert'>();
  const { data: feed } = useFeedDetail('10'); // params로 feedId 받아오기
  const { comments, hasNextPage, fetchNextPage } = useGetComments('10'); // params로 feedId 받아오기 => 그냥  feed의 id에서 꺼내온걸로 userInfo에도 내려주는걸 고려하기
  const wrapperRef = useRef(null);
  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
    rootRef: wrapperRef,
  });
  const { mutate: commentMutate } = usePostComment('10');
  const { navigateToHome } = usePageNavigator();
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });
  console.log(feed);
  console.log('comment', comments);

  const MOCK = {
    id: '2',
    member: {
      id: '2',
      imageUrl: 'www.google.com/',
      nickname: '박콩불2',
      tasteMood: { id: '1', name: '베지테리안' },
    },
    createdAt: '2023-10-17T16:54:03',
    updatedAt: '2023-10-18T11:54:03',
    location: '맛있게 매운 콩볼 범계점',
    review:
      '맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.',
    storeMood: [
      { id: '1', name: '따뜻함' },
      { id: '2', name: '가족과함께' },
      { id: '3', name: '가게무드' },
    ],
    images: [
      {
        id: '1',
        imageUrl:
          'https://img.daily.co.kr/@files/www.daily.co.kr/content/food/2020/20200730/40d0fb3794229958bdd1e36520a4440f.jpg',
        menu: {
          name: '마라탕',
          rating: 4,
        },
      },
      {
        id: '2',
        imageUrl:
          'https://img.daily.co.kr/@files/www.daily.co.kr/content/food/2020/20200730/40d0fb3794229958bdd1e36520a4440f.jpg',
        menu: {
          name: '감자탕',
          rating: 3,
        },
      },
    ],
    likeCount: 17,
    isLiked: true,
    commentCount: 3,
  };

  const handleSubmit = () => {
    console.log('is Comment Valid', {
      isCommentValid: isValid,
      commentValue: value,
    });
    handleChange('');

    commentMutate({
      // feedId: MOCK.id,
      // feedId: '10',
      feedId: feed.id,
      content: value,
    });
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
          <Carousel images={MOCK.images} />

          <Content>
            <Info>
              <Detail>
                <FeedUserInfo
                  member={MOCK.member}
                  createdAt={MOCK.createdAt}
                  location={MOCK.location}
                  feedId={feed?.id}
                />
              </Detail>
              <Review>{MOCK.review}</Review>
              <StoreMoodList>
                {MOCK.storeMood.map((storeMood) => (
                  <Badge variant="store" badge={storeMood} key={storeMood.id} />
                ))}
              </StoreMoodList>
            </Info>
            <FeedAction
              // likeCount={MOCK.likeCount}
              // commentCount={MOCK.commentCount}
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
                  <CommentBox
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
