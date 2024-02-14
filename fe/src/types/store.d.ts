type StoreItem = {
  address: string;
  roadAddress: string;
} & SelectedStore;

type SelectedStore = {
  id: string;
  name: string | null; // TODO null은 들어갈 수 없음 수정요청
};

type StoreDetail = {
  name: string;
  rating: number;
  liked: boolean;
  likeCount: number;
  feedCount: number;
  address: string;
  roadAddress: string;
  phone: string;
  x: number;
  y: number;
  closed: boolean;
};
