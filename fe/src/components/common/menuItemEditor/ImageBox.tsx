import React, { useRef, useState } from 'react';
import { useToast } from 'recoil/toast/useToast';
import { usePostImage } from 'service/queries/imageUpload';
import { styled } from 'styled-components';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { PlusGhostIcon } from '../icon/icons';

type Props = {
  menuId: string;
  imageUrl?: string;
  onEditMenuImage(id: string, image: ImageType): void;
};

export const ImageBox: React.FC<Props> = ({
  menuId,
  imageUrl,
  onEditMenuImage,
}) => {
  const toast = useToast();
  const { mutate: imageMutate } = usePostImage('feed');

  const inputRef = useRef<HTMLInputElement>(null);

  const [imageData, setImageData] = useState({
    id: '',
    url: imageUrl,
  });

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
        onEditMenuImage(menuId, res);
      },
    });

    if (inputRef.current) {
      inputRef.current.value = '';
    }
  };

  return (
    <ImageWrapper $isImageUrl={!!imageUrl} onClick={handleImageClick}>
      <input
        ref={inputRef}
        type="file"
        accept=".png, .jpg, .jpeg"
        onChange={handleUploadImage}
      />
      <img
        src={imageData.url || generateDefaultUserImage(menuId)}
        alt="menu item image"
      />
      <PlusGhostIcon />
    </ImageWrapper>
  );
};

const ImageWrapper = styled.div<{ $isImageUrl: boolean }>`
  width: 95px;
  height: 95px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;

  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 95px;
    height: 95px;
    background-color: rgba(0, 0, 0, 0.3);
    pointer-events: none;
    cursor: pointer;
  }

  input[type='file'] {
    display: none;
    &::file-selector-button {
      display: none;
    }
  }

  img {
    width: 95px;
    height: 95px;
    object-fit: cover;
  }

  svg {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 10;
  }
`;
