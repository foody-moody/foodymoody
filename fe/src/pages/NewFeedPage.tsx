import { useState } from 'react';
import { useParams } from 'react-router-dom';
import { styled } from 'styled-components';
import { customScrollStyle, flexColumn, flexRow } from 'styles/customStyle';
import { media } from 'styles/mediaQuery';
import { BadgeSelector } from 'components/common/badgeSelector/BadgeSelector';
import { Button } from 'components/common/button/Button';
import { TextButton } from 'components/common/button/TextButton';
import { Dim } from 'components/common/dim/Dim';
import { PlusIcon } from 'components/common/icon/icons';
import { Input } from 'components/common/input/Input';
import { MenuItemEditor } from 'components/common/menuItemEditor/MenuItemEditor';
import { TextArea } from 'components/common/textarea/Textarea';
// import { useFeedDetail, useFeedEditor } from 'queries/feed';
import { useFeedEditor } from 'queries/feed';
import { useInput } from 'hooks/useInput';
import { useMenuItem } from 'hooks/useMenuItem';
import { usePageNavigator } from 'hooks/usePageNavigator';

// const MOCK_MENU = {
//   id: 1,
//   location: '안양하다 닭꼬치 먹고싶',
//   createdAt: '2023-10-17T16:54:03',
//   updatedAt: '2023-10-18T19:54:03',
//   review: '맛있게 먹었습니다.',
//   mood: '기쁨',
//   images: [
//     {
//       id: crypto.randomUUID(),
//       imageUrl: 'https://www.googles.com/',
//       menu: {
//         name: '마라탕',
//         rating: 4,
//       },
//     },
//     {
//       id: crypto.randomUUID(),
//       imageUrl: 'https://www.google.com/',
//       menu: {
//         name: '감자탕',
//         rating: 3,
//       },
//     },
//   ],
//   likeCount: 17,
//   isLiked: true,
//   commentCount: 3,
// };

export const NewFeedModalPage = () => {
  const { navigateToHome } = usePageNavigator();
  const { id: feedId } = useParams() as { id: string };
  const [selectedBadgeList, setSelectedBadgeList] = useState<Badge[]>([]);
  const { mutate: feedMutate } = useFeedEditor(feedId);
  // const { data: feedDetailData } = useFeedDetail(feedId);
  const {
    menuItems,
    handleAddMenuItem,
    handleRemoveMenuItem,
    handleEditMenuName,
    handleEditStarRating,
  } = useMenuItem(); // feedDetailData로 교체
  const {
    value: locationName,
    handleChange: handleLocationChange,
    isValid: isLocationNameVaild,
    helperText: locationNameHelperText,
  } = useInput({
    initialValue: '',
    validator: (value) => value.trim().length > 0,
    helperText: '가게 이름을 입력해주세요',
  });
  const { value: reviewValue, handleChange: handleReviewChange } = useInput({
    initialValue: '',
  });

  const handleSubmit = () => {
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
  };

  const isValid =
    isLocationNameVaild &&
    reviewValue.trim().length > 0 &&
    selectedBadgeList.length > 0 &&
    menuItems
      .map((menuItem) => menuItem.menu.name)
      .every((name) => name.trim().length > 0);

  return (
    <>
      <Dim onClick={navigateToHome} />
      <Wrapper>
        <Box>
          <Top>
            <TextButton color="black" onClick={navigateToHome}>
              취소
            </TextButton>
            <TextButton
              color="black"
              disabled={!isValid}
              onClick={handleSubmit}
            >
              게시
            </TextButton>
          </Top>
          <Content>
            <Div>
              <InputBox>
                <Title>가게 이름을 작성해주세요</Title>
                <Input
                  value={locationName}
                  variant="underline"
                  onChangeValue={handleLocationChange}
                  helperText={locationNameHelperText}
                />
              </InputBox>

              <MenuEditorWrapper>
                <Title>리뷰를 작성 할 메뉴를 등록해주세요</Title>
                <MenuEditor>
                  {menuItems.map((menuItem) => (
                    <MenuItemEditor
                      key={menuItem.id}
                      menuItem={menuItem}
                      onEditMenuName={handleEditMenuName}
                      onEditStarRating={handleEditStarRating}
                      onRemove={handleRemoveMenuItem}
                    />
                  ))}
                </MenuEditor>
              </MenuEditorWrapper>
              <AddButton>
                <Button
                  size="s"
                  backgroundColor="orange"
                  width={166}
                  disabled={menuItems.length === 3}
                  onClick={handleAddMenuItem}
                >
                  <PlusIcon />
                  메뉴 추가
                </Button>
              </AddButton>
            </Div>
            <Div>
              <MoodSelector>
                <Title>가게의 무드를 선택해주세요</Title>
                <Caption>(무드는 최대 3가지를 고를 수 있어요)</Caption>

                <BadgeSelector
                  variant="store"
                  maxCount={3}
                  selectedBadgeList={selectedBadgeList}
                  onActiveBadge={handleSelectBadgeList}
                />
              </MoodSelector>
              <TextareaBox>
                <Title>가게의 리뷰를 작성해주세요</Title>
                <TextArea
                  value={reviewValue}
                  placeholder="리뷰를 입력해주세요"
                  onChange={handleReviewChange}
                />
              </TextareaBox>
            </Div>
          </Content>
        </Box>
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  padding: 16px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 100;
  max-width: 968px;
  min-width: 343px;
  width: 100%;
  height: 680px;

  ${media.md} {
    max-width: 580px;
    min-width: 379px;
    width: 100%;
    max-height: 88dvh;
    height: 100%;
    overflow: hidden;
  }
`;

const Box = styled.div`
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  padding: 16px 24px 32px 24px;
  overflow-y: hidden;
  max-height: 968px;
  height: 100%;
  background-color: ${({ theme: { colors } }) => colors.white};

  ${media.md} {
    flex-direction: column;
    max-width: 580px;
    width: 100%;
    max-height: 100%;
    overflow-y: auto;
    height: 100%;
    padding: 16px 12px 32px 24px;

    ${customScrollStyle}
  }
`;

const Top = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
`;

const Content = styled.div`
  ${flexRow}
  gap: 24px;
  height: 100%;

  ${media.md} {
    ${flexColumn}
    overflow: visible;
    max-height: 100%;
    height: 100%;
    box-sizing: border-box;
  }
`;

const Div = styled.div`
  flex: 1;
`;

const InputBox = styled.div`
  ${flexColumn}
  gap: 8px;
  padding: 0px 0px 16px 0;
`;

const TextareaBox = styled.div`
  ${flexColumn}
  gap: 8px;
  padding: 0px 0px 24px 0;
  max-height: 300px;
  height: 100%;
`;

const MenuEditorWrapper = styled.div`
  ${flexColumn}
  padding: 0px 0px 16px 0;
`;

const MenuEditor = styled.div`
  margin-top: -8px;
`;

const AddButton = styled.div`
  padding: 12px 0;
  width: 100%;
  ${flexRow}
  justify-content: center;
`;

const MoodSelector = styled.div`
  ${flexColumn}
  gap: 8px;
  padding-bottom: 24px;
`;

const Title = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
`;

const Caption = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
