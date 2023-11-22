import { useState } from 'react';

const DEFAULT_MENU_ITEM = {
  id: self.crypto.randomUUID(),
  imageUrl: '',
  menu: {
    name: '',
    rating: 0,
  },
};

export const useMenuItem = (initialMenuItems?: FeedImage[]) => {
  const [menuItems, setMenuItems] = useState(
    initialMenuItems || [DEFAULT_MENU_ITEM]
  );

  const handleAddMenuItem = () => {
    if (menuItems.length >= 3) {
      console.log(`3개이상 등록불가`);
      return;
    }
    const newItem = { ...DEFAULT_MENU_ITEM, id: self.crypto.randomUUID() };
    setMenuItems((prevItems) => [...prevItems, newItem]);
  };

  const handleRemoveMenuItem = (id: string) => {
    if (menuItems.length === 1) {
      console.log('1개 이상 필수');
      return;
    }
    const newItems = menuItems.filter((item) => item.id !== id);
    console.log(newItems, 'newItems');

    setMenuItems(newItems);
  };

  const updateMenu = (
    id: string,
    updates: Partial<(typeof DEFAULT_MENU_ITEM)['menu']>
  ) => {
    const newItems = menuItems.map((item) =>
      item.id === id ? { ...item, menu: { ...item.menu, ...updates } } : item
    );
    setMenuItems(newItems);
  };

  const handleEditMenuName = (id: string, name: string) => {
    updateMenu(id, { name });
  };

  const handleEditStarRating = (id: string, rating: number) => {
    updateMenu(id, { rating });
  };

  return {
    menuItems,
    handleAddMenuItem,
    handleRemoveMenuItem,
    handleEditMenuName,
    handleEditStarRating,
  };
};
