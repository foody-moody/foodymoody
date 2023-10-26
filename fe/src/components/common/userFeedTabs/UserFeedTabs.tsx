import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';

type Props = {
  index: number;
  onClick: (index: number) => void;
};

export const UserFeedTabs: React.FC<Props> = ({ index, onClick }) => {
  const handleTabClick = (index: number) => {
    onClick(index);
  };

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
            onClick={() => handleTabClick(tab.id)}
            $id={tab.id}
            $index={index}
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

const Tab = styled.div<{ $id: number; $index: number }>`
  padding: 4px 0;
  transition: all 0.2s ease-in-out;

  border-bottom: ${({ $index, $id }) =>
    $index === $id ? '3px solid black' : '3px solid transparent'};

  color: ${({ $index, $id, theme: { colors } }) =>
    $index === $id ? colors.textPrimary : colors.textTertiary};

  font: ${({ theme: { fonts } }) => fonts.displayM14};

  ${media.xs} {
    font: ${({ theme: { fonts } }) => fonts.displayM12};
  }
`;
