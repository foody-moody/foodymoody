import { useState, useEffect } from 'react';

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

  useEffect(() => {
    if (initialMenuItems) {
      setMenuItems(initialMenuItems);
    } else {
      setMenuItems([DEFAULT_MENU_ITEM]);
    }
  }, [initialMenuItems]);

  const handleAddMenuItem = () => {
    if (menuItems.length === 3) {
      console.log('can not add more than 3 items');
      // TODO : 에러 메시지 띄우는 방식 고려
      return;
    }
    setMenuItems((prevItems) => [...prevItems, DEFAULT_MENU_ITEM]);
  };

  const handleRemoveMenuItem = (index: number) => {
    if (menuItems.length === 1) {
      console.log('can not remove last item');
      // TODO : 에러 메시지 띄우는 방식 고려
      return;
    }

    setMenuItems((prevItems) =>
      prevItems.filter((_, itemIndex) => itemIndex !== index)
    );
  };

  return { menuItems, handleAddMenuItem, handleRemoveMenuItem };
};
