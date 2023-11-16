import { useRecoilState } from 'recoil';
import { toastListState } from './atom';

export const useToast = () => {
  const [toasts, setToasts] = useRecoilState(toastListState);

  const addToast = (type: ToastType, message: string) => {
    const id = crypto.randomUUID();
    setToasts([...toasts, { id, type, message, isVisible: true }]);

    setTimeout(() => {
      setToasts((currentToasts) =>
        currentToasts.map((toast) =>
          toast.id === id ? { ...toast, isVisible: false } : toast
        )
      );

      setTimeout(() => {
        setToasts((currentToasts) =>
          currentToasts.filter((toast) => toast.id !== id)
        );
      }, 500);
    }, 3000);
  };

  return {
    error: (message: string) => addToast('error', message),
    noti: (message: string) => addToast('noti', message),
    success: (message: string) => addToast('success', message),
  };
};
