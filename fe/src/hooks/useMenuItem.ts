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
    if (menuItems.length >= 3) {
      // TODO 상수화, 헬퍼메시지 위치
      console.log(`3개이상 등록불가`);
      return;
    }

    setMenuItems((prevItems) => [...prevItems, DEFAULT_MENU_ITEM]);
  };

  const handleRemoveMenuItem = (index: number) => {
    if (menuItems.length === 1) {
      console.log('1개 이상 필수');
      return;
    }

    const newItems = menuItems.filter((_, itemIndex) => itemIndex !== index);
    setMenuItems(newItems);
  };

  const updateMenu = (
    index: number,
    updates: Partial<(typeof DEFAULT_MENU_ITEM)['menu']>
  ) => {
    const newItems = [...menuItems];
    newItems[index] = {
      ...newItems[index],
      menu: { ...newItems[index].menu, ...updates },
    };
    setMenuItems(newItems);
  };

  const handleEditMenuName = (index: number, name: string) => {
    updateMenu(index, { name });
  };

  const handleEditStarRating = (index: number, numStar: number) => {
    updateMenu(index, { numStar });
  };

  return {
    menuItems,
    handleAddMenuItem,
    handleRemoveMenuItem,
    handleEditMenuName,
    handleEditStarRating,
  };
};
