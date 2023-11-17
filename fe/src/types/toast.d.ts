type ToastType = 'error' | 'success' | 'noti';

type ToastItem = {
  id: string;
  type: ToastType;
  message: string;
  isVisible: boolean;
};
