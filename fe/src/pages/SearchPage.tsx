import { useState } from 'react';
import { useParams } from 'react-router-dom';
import { styled } from 'styled-components';
import { v4 as uuidv4 } from 'uuid';
import { BadgeSelector } from 'components/common/badgeSelector/BadgeSelector';
import { Button } from 'components/common/button/Button';
import { PlusIcon } from 'components/common/icon/icons';
import { Input } from 'components/common/input/Input';
import { MenuItemEditor } from 'components/common/menuItemEditor/MenuItemEditor';
import { TextArea } from 'components/common/textarea/Textarea';
import { useFeedDetail, useFeedEditor } from 'queries/feed';
import { useInput } from 'hooks/useInput';
import { useMenuItem } from 'hooks/useMenuItem';

const MOCK_MENU = {
  id: 1,
  location: '맛있게 매운 콩볼 범계점',
  createdAt: '2023-10-17T16:54:03',
  updatedAt: '2023-10-18T19:54:03',
  review: '맛있게 먹었습니다.',
  mood: '기쁨',
  images: [
    {
      id: uuidv4(),
      imageUrl: 'https://www.googles.com/',
      menu: {
        name: '마라탕',
        rating: 4,
      },
    },
    {
      id: uuidv4(),
      imageUrl: 'https://www.google.com/',
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

export const SearchPage = () => {
  const { id: feedId } = useParams() as { id: string };
  const {
    value: locationName,
    handleChange: handleLocationChange,
    isValid: isLocationNameVaild,
    helperText: locationNameHelperText,
  } = useInput({
    initialValue: MOCK_MENU.location,
    validator: (value) => value.trim().length > 0,
    helperText: '가게 이름을 입력해주세요',
  });
  const [selectedBadgeList, setSelectedBadgeList] = useState<Badge[]>([]);
  const {
    value: reviewValue,
    handleChange: handleReviewChange,
    isValid: isReviewValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.trim().length > 4,
    helperText: '리뷰는 5자 이상 입력해주세요',
  });
  const { mutate: feedMutate } = useFeedEditor(feedId);
  //   const { id: feedId } = useParams(); 이거 써서 detail조회 해서 정보 data 가져오기

  const { data: feedDetailData } = useFeedDetail(feedId);

  const {
    menuItems,
    handleAddMenuItem,
    handleRemoveMenuItem,
    handleEditMenuName,
    handleEditStarRating,
  } = useMenuItem(MOCK_MENU.images); // feedDetailData로 교체해보기

  const handleSubmit = () => {
    console.log('submit triggered');
    const isAllValid = true;
    if (!isAllValid) {
      return;
    }

    console.log('submit location', locationName);
    console.log(
      'submit menus',
      menuItems.map((menuItem) => menuItem.menu)
    );
    console.log(
      'submit Badge',
      selectedBadgeList.map((badge) => badge.name)
    );
    console.log('submit review', reviewValue);

    feedMutate({
      location: locationName,
      images: menuItems.map(({ imageUrl, menu }) => ({ imageUrl, menu })),
      storeMood: selectedBadgeList.map((badge) => badge.name),
      review: reviewValue,
    });
  };

  const handleSelectBadgeList = (badges: Badge[]) => {
    setSelectedBadgeList(badges);
    console.log('Active badges:', selectedBadgeList);
  };

  return (
    <PageWrapper>
      <FlexWrapper>
        <h1>Example</h1>
        <button onClick={handleSubmit}>게시</button>
        <button onClick={handleAddMenuItem}>추가</button>
        <button
          onClick={() => {
            console.log('menuItems', menuItems);
          }}
        >
          로그
        </button>
        <Title>가게 이름을 작성해주세요</Title>
        <Input
          variant="ghost"
          onChangeValue={handleLocationChange}
          helperText={locationNameHelperText}
        />

        <Title>리뷰를 작성 할 메뉴를 등록해주세요</Title>

        <FlexWrapper>
          {menuItems.map((menuItem) => (
            <MenuItemEditor
              key={menuItem.id}
              menuItem={menuItem}
              onEditMenuName={handleEditMenuName}
              onEditStarRating={handleEditStarRating}
              onRemove={handleRemoveMenuItem}
            />
          ))}
          <Button
            size="s"
            backgroundColor="orange"
            width={166}
            disabled={menuItems.length === 3}
          >
            <PlusIcon />
            메뉴 추가
          </Button>
          <Title>가게의 무드를 선택해주세요</Title>
          <Caption>(무드는 최대 3가지를 고를 수 있어요)</Caption>

          <BadgeSelector
            variant="store"
            maxCount={3}
            selectedBadgeList={selectedBadgeList}
            onActiveBadge={handleSelectBadgeList}
          />

          <Title>가게의 리뷰를 작성해주세요</Title>
          <TextArea
            value={reviewValue}
            placeholder="리뷰를 입력해주세요"
            onChange={handleReviewChange}
          />
        </FlexWrapper>
      </FlexWrapper>
    </PageWrapper>
  );
};

const PageWrapper = styled.div`
  width: 800px;
  h1 {
    font: ${({ theme: { fonts } }) => fonts.displayB24};
  }
`;

const FlexWrapper = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 16px;
`;

const Title = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
`;

const Caption = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
