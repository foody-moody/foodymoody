import { useRef, useState } from 'react';
import { useToast } from 'recoil/toast/useToast';
import { usePostImage } from 'service/queries/imageUpload';

type useImageUploadType = {
  type: 'feed' | 'user';
  initialUrl?: string;
  callbackFn?: () => void;
};

export const useImageUpload = ({ type, initialUrl }: useImageUploadType) => {
  const toast = useToast();
  const { mutate: imageMutate } = usePostImage(type);
  const [imageData, setImageData] = useState({
    id: '',
    url: initialUrl,
  });

  const inputRef = useRef<HTMLInputElement>(null);

  const handleImageClick = () => {
    if (inputRef.current) {
      inputRef.current.click();
    }
  };

  const handleUploadImage = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];

    if (!file) {
      return;
    }

    const ALLOWED_TYPES = ['image/png', 'image/jpg', 'image/jpeg'];
    const MAX_FILE_SIZE_BYTES = 1024 * 1024 * 2; // 2MB

    if (!ALLOWED_TYPES.includes(file.type) || file.size > MAX_FILE_SIZE_BYTES) {
      toast.noti(
        '이미지는 2MB 이하의 png, jpg, jpeg 파일만 업로드 가능합니다.'
      );
      return;
    }

    const formData = new FormData();
    formData.append('file', file);

    imageMutate(formData, {
      onSuccess: (res) => {
        console.log(res, ' now res');
        setImageData(res);
        // onEditMenuImage(menuId, res.id);
        // onEditMenuImage(menuId, imageData.id);
        // callbackFn();
      },
    });

    if (inputRef.current) {
      inputRef.current.value = '';
    }
  };

  return { inputRef, handleImageClick, handleUploadImage, imageData };
};
