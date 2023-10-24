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
      // TODO 상수화
      console.log(`Can't add more than ${3} items.`);
      return;
    }

    setMenuItems((prevItems) => [...prevItems, DEFAULT_MENU_ITEM]);
  };

  const handleRemoveMenuItem = (index: number) => {
    if (menuItems.length === 1) {
      console.log('Can not remove the last item');
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
