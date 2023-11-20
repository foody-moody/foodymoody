import { useState } from 'react';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { UserFeedTabs } from 'components/common/userFeedTabs/UserFeedTabs';
import { ProfileUserInfo } from 'components/profileUserInfo/ProfileUserInfo';

// import { useGetProfile } from 'queries/profile';

export const ProfilePage = () => {
  // const { data } = useGetProfile();
  const [index, setIndex] = useState(0);

  const handleFeedTab = (index: number) => {
    setIndex(index);
  };

  return (
    <Wrapper>
      <ContentWrapper>
        <ProfileWrapper>
          <ProfileUserInfo member={MOCK} />
        </ProfileWrapper>
        <UserFeedTabs index={index} onClick={handleFeedTab} />
        <FeedsWrapper>
          {MOCK_FEEDS.map((feed) => (
            <img
              key={feed.id}
              src={feed.imageUrl}
              alt={feed.imageUrl}
              onClick={() => {}}
            />
          ))}
        </FeedsWrapper>
      </ContentWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  transition: all 0.2s ease-in-out;
`;

const ContentWrapper = styled.div`
  width: 566px;
  height: 100%;
  border-left: 1px solid ${({ theme: { colors } }) => colors.black};
  border-right: 1px solid ${({ theme: { colors } }) => colors.black};

  ${media.md} {
    max-width: 568px;
    width: 100%;
  }

  ${media.xs} {
    border-left: none;
    border-right: none;
  }
`;

const ProfileWrapper = styled.div`
  padding: 24px 16px 16px 16px;
  border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};

  ${media.xs} {
    padding: 16px;
  }
`;

const FeedsWrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2px;

  img {
    cursor: pointer;
  }
`;

const generateDefaultImage = (imageUrl: string) =>
  `https://source.boringavatars.com/beam/${imageUrl}?colors=FF4E50,FC913A,F9D423,EDE574,E1F5C4&square`;

const MOCK_FEEDS = Array.from({ length: 20 }, (_, index) => ({
  id: index + 1,
  imageUrl: generateDefaultImage(`githubrandomProfileimageurl${index + 1}`),
}));

const MOCK = {
  memberId: '1',
  imageUrl: 'https://www.dskadsl.com',
  nickname: '보노',
  image: 'url',
  email: 'test@email.com',
  mood: '기쁨',
  myFeeds: [
    {
      id: '1',
      imageUrl: 'https://www.googles.com/',
    },
    {
      id: '2',
      imageUrl: 'https://www.googles.com/',
    },
  ],
  myFeedsCount: 7,
};
