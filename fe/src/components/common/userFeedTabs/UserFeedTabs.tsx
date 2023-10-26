import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';

type Props = {
  index: number;
  onClick: (index: number) => void;
};

export const UserFeedTabs: React.FC<Props> = ({ index, onClick }) => {
  const Tabs = [
    {
      id: 0,
      title: '게시물',
    },
    {
      id: 1,
      title: '콜렉션',
    },
    {
      id: 2,
      title: '좋아요',
    },
  ];

  return (
    <Wrapper>
      <TabWrapper>
        {Tabs.map((tab) => (
          <Tab
            key={tab.id}
            onClick={() => onClick(tab.id)}
            $isActive={tab.id === index}
          >
            {tab.title}
          </Tab>
        ))}
      </TabWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;

const TabWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 244px;
`;

const Tab = styled.div<{ $isActive: boolean }>`
  padding: 4px 0;
  transition: all 0.2s ease-in-out;

  border-bottom: ${({ $isActive }) =>
    $isActive ? '3px solid black' : '3px solid transparent'};

  color: ${({ $isActive, theme: { colors } }) =>
    $isActive ? colors.textPrimary : colors.textTertiary};

  font: ${({ theme: { fonts } }) => fonts.displayM14};

  ${media.xs} {
    font: ${({ theme: { fonts } }) => fonts.displayM12};
  }
`;
