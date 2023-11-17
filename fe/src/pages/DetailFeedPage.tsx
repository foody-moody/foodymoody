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
import { usePostComment } from 'queries/comment';
import { useInput } from 'hooks/useInput';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const DetailFeedModalPage = () => {
  /* TODO. 주소로 Detail 데이터 가져오깅 */
  const { mutate: commentMutate } = usePostComment();
  const { navigateToHome } = usePageNavigator();
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });

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

    commentMutate({
      feedId: MOCK.id,
      content: value,
    });
  };

  return (
    <>
      <Dim onClick={navigateToHome} />
      <Wrapper>
        <Box>
          <Carousel images={MOCK.images} />

          <Content>
            <Info>
              <Detail>
                <FeedUserInfo
                  member={MOCK.member}
                  createdAt={MOCK.createdAt}
                  location={MOCK.location}
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
              likeCount={MOCK.likeCount}
              commentCount={MOCK.commentCount}
            />
            <CommentContainer>
              <CommentInput
                value={value}
                limitedLength={200}
                onChangeValue={handleChange}
                onSubmitComment={handleSubmit}
              />
              <Comment>
                {Array.from({ length: 7 }).map((_, index) => (
                  <CommentBox
                    key={index}
                    imageUrl="이미지"
                    nickname="댓글닉넴"
                    createdAt="2023-11-01T14:55:47.88735"
                    content="댓글임니당 ㅎㅎ댓글임니당댓글임니당댓글임니당댓글임니당댓글임니당댓글임니당"
                    hasReply={index % 2 === 0}
                    replyCount={index % 2 === 0 ? 3 : 0}
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
