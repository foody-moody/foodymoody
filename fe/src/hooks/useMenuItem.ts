import { useState } from 'react';

const DEFAULT_MENU_ITEM = {
  imageUrl: '',
  menu: {
    name: '',
    numStar: 0,
  },
};

export const useMenuItem = (initialMenuItems?: FeedImageType[]) => {
  const [menuItems, setMenuItems] = useState(
    initialMenuItems || [DEFAULT_MENU_ITEM]
  );

  const handleAddMenuItem = () => {
    if (menuItems.length === 3) {
      console.log('can not add more than 3 items');
      // TODO : 헬퍼 메시지 띄우는 방식 고려
      return;
    }

    setMenuItems((prevItems) => [...prevItems, DEFAULT_MENU_ITEM]);
  };

  const handleRemoveMenuItem = (index: number) => {
    if (menuItems.length === 1) {
      console.log('can not remove last item');
      // TODO : 헬퍼 메시지 띄우는 방식 고려
      return;
    }

    setMenuItems((prevItems) =>
      prevItems.filter((_, itemIndex) => itemIndex !== index)
    );
  };

  const handleEditMenuName = (index: number, name: string) => {
    setMenuItems((prevItems) => {
      // 복사
      const newItems = [...prevItems];

      // 특정 index의 항목을 복사해서 변경
      const updatedItem = {
        ...newItems[index],
        menu: { ...newItems[index].menu, name: name },
      };

      // 변경된 항목으로 교체
      newItems[index] = updatedItem;

      return newItems;
    });
  };

  const handleEditStarRating = (index: number, rating: number) => {
    setMenuItems((prevItems) => {
      // 복사
      const newItems = [...prevItems];

      // 특정 index의 항목을 복사해서 변경
      const updatedItem = {
        ...newItems[index],
        menu: { ...newItems[index].menu, numStar: rating },
      };

      // 변경된 항목으로 교체
      newItems[index] = updatedItem;

      return newItems;
    });
  };
  return {
    menuItems,
    handleAddMenuItem,
    handleRemoveMenuItem,
    handleEditMenuName,
    handleEditStarRating,
  };
};
