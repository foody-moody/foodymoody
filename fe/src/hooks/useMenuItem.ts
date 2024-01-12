import { useState, useEffect } from 'react';
import { useToast } from 'recoil/toast/useToast';

const DEFAULT_MENU_ITEM = {
  id: self.crypto.randomUUID(),
  image: {
    id: '',
    url: '',
  },
  menu: {
    name: '',
    rating: 0,
  },
};

export const useMenuItem = (initialMenuItems?: FeedImage[]) => {
  const toast = useToast();
  const [menuItems, setMenuItems] = useState(
    initialMenuItems || [DEFAULT_MENU_ITEM]
  );

  useEffect(() => {
    initialMenuItems && setMenuItems(initialMenuItems);
  }, [initialMenuItems]);

  const handleAddMenuItem = () => {
    if (menuItems.length >= 3) {
      toast.noti('3개 이상 등록할 수 없어요.');
      return;
    }
    const newItem = { ...DEFAULT_MENU_ITEM, id: self.crypto.randomUUID() };
    setMenuItems((prevItems) => [...prevItems, newItem]);
  };

  const handleRemoveMenuItem = (id: string) => {
    if (menuItems.length === 1) {
      console.log('1개 이상 필수');
      toast.noti('1개 이상 등록해주세요.');
      return;
    }
    const newItems = menuItems.filter((item) => item.id !== id);

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

  const updateImage = (id: string, updates: Partial<ImageType>) => {
    const newItems = menuItems.map((item) =>
      item.id === id ? { ...item, image: { ...item.image, ...updates } } : item
    );
    setMenuItems(newItems);
  };

  const handleEditMenuImage = (id: string, image: ImageType) => {
    updateImage(id, image);
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
    handleEditMenuImage,
    handleEditMenuName,
    handleEditStarRating,
  };
};
