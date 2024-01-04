import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useFeedDetail, useFeedEditor } from 'service/queries/feed';
import { styled } from 'styled-components';
import { customScrollStyle, flexColumn, flexRow } from 'styles/customStyle';
import { media } from 'styles/mediaQuery';
import { StoreMoodSelector } from 'components/common/badge/StoreMoodSelector';
import { Button } from 'components/common/button/Button';
import { TextButton } from 'components/common/button/TextButton';
import { PlusIcon } from 'components/common/icon/icons';
import { MenuItemEditor } from 'components/common/menuItemEditor/MenuItemEditor';
import { TextArea } from 'components/common/textarea/Textarea';
import { SearchLocation } from 'components/searchLocation/SearchLocation';
import { useInput } from 'hooks/useInput';
import { useMenuItem } from 'hooks/useMenuItem';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const FeedEditor: React.FC = () => {
  const { navigateToHome } = usePageNavigator();
  const [selectedBadgeList, setSelectedBadgeList] = useState<Badge[]>([]);

  const { id: feedId } = useParams() as { id: string };
  const { mutate: feedMutate } = useFeedEditor(feedId);
  const { data: feedDetailData } = useFeedDetail(feedId);
  const {
    menuItems,
    handleAddMenuItem,
    handleRemoveMenuItem,
    handleEditMenuImage,
    handleEditMenuName,
    handleEditStarRating,
  } = useMenuItem(feedDetailData?.images);

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

  useEffect(() => {
    if (feedDetailData) {
      handleReviewChange(feedDetailData.review);
      handleLocationChange(feedDetailData.location);
      setSelectedBadgeList(feedDetailData.storeMood); // 확인필요
    }
  }, [feedDetailData]);

  const handleSubmit = () => {
    feedMutate({
      location: locationName,
      images: menuItems.map(({ image, menu }) => ({
        imageId: image.id,
        menu,
      })),
      storeMood: selectedBadgeList.map((badge) => badge.id),
      review: reviewValue,
    });
  };

  const handleSelectBadgeList = (badges: Badge[]) => {
    setSelectedBadgeList(badges);
  };

  const isValid =
    isLocationNameVaild &&
    reviewValue.trim().length > 0 &&
    menuItems
      .map((menuItem) => menuItem.image.id)
      .every((name) => name.trim().length > 0) &&
    selectedBadgeList.length > 0 &&
    menuItems
      .map((menuItem) => menuItem.menu.name)
      .every((name) => name.trim().length > 0);

  return (
    <Wrapper>
      <Top>
        <TextButton color="black" onClick={navigateToHome}>
          취소
        </TextButton>
        <TextButton color="black" disabled={!isValid} onClick={handleSubmit}>
          게시
        </TextButton>
      </Top>
      <Content>
        <Div>
          <InputBox>
            <Title>가게를 등록해주세요</Title>
            <SearchLocation
              locationName={locationName}
              locationNameHelperText={locationNameHelperText}
              handleLocationChange={handleLocationChange}
            />
          </InputBox>
          <MenuEditorWrapper>
            <Title>리뷰를 작성 할 메뉴를 등록해주세요</Title>
            <MenuItemContainer>
              {menuItems.map((menuItem) => (
                <MenuItemEditor
                  key={menuItem.id}
                  menuItem={menuItem}
                  onEditMenuImage={handleEditMenuImage}
                  onEditMenuName={handleEditMenuName}
                  onEditStarRating={handleEditStarRating}
                  onRemove={handleRemoveMenuItem}
                />
              ))}
            </MenuItemContainer>
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
            <StoreMoodSelector
              selectedBadges={selectedBadgeList}
              onSelectedBadges={handleSelectBadgeList}
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
    </Wrapper>
  );
};

const Wrapper = styled.div`
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

const MenuItemContainer = styled.div`
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

// const DummyMap = styled.div`
//   width: 100%;
//   height: 300px;
//   background-color: ${({ theme: { colors } }) => colors.black};
// `;
