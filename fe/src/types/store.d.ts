type StoreItem = {
  address: string;
  roadAddress: string;
} & SelectedStore;

type SelectedStore = {
  id: string;
  name: string;
};

type StoreDetail = {
  name: string;
  address: string;
  roadAddress: string;
  phone: string;
  x: number;
  y: number;
  closed: boolean;
};
