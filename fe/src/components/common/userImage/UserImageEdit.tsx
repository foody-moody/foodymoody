import React, { useRef, useState } from 'react';
import { useToast } from 'recoil/toast/useToast';
import { usePostImage } from 'service/queries/imageUpload';
import { useEditProfileImage } from 'service/queries/profile';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { useAuthState } from 'hooks/auth/useAuth';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { resizeImage } from 'utils/resizeImage';
import { EditIcon } from '../icon/icons';
import { useModal } from '../modal/useModal';
import { UserImage } from './UserImage';

type Props = {
  isAuthor: boolean;
  imageId?: string;
  imageUrl?: string;
};

export const UserImageEdit: React.FC<Props> = ({
  isAuthor,
  imageId,
  imageUrl,
}) => {
  const [imageData, setImageData] = useState({
    id: imageId,
    url: imageUrl,
  });
  const { userInfo } = useAuthState();
  const { mutate: imageMutate, isLoading: isUploadLoading } =
    usePostImage('user');
  const { mutate: profileImageMutate } = useEditProfileImage(userInfo.id);
  const { openModal, closeModal } = useModal<'profileImageAlert'>();
  const toast = useToast();
  const inputRef = useRef<HTMLInputElement>(null);

  const defaultImage = generateDefaultUserImage(userInfo?.id);
  const userImage = imageData.url || defaultImage;

  const handleImageClick = () => {
    if (isUploadLoading) {
      return;
    }

    if (inputRef.current) {
      inputRef.current.click();
    }
  };

  const handleUploadImage = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    console.log('file', file);
    console.log('file', typeof file);

    if (!file) {
      return;
    }

    const ALLOWED_TYPES = [
      'image/png',
      'image/jpg',
      'image/jpeg',
      'image/webp',
    ];
    const MAX_FILE_SIZE_BYTES = 1024 * 1024 * 2.6; // 2MB

    if (!ALLOWED_TYPES.includes(file.type) || file.size > MAX_FILE_SIZE_BYTES) {
      toast.noti(
        '이미지는 2.6MB 이하의 png, jpg, jpeg 파일만 업로드 가능합니다.'
      );
      return;
    }

    const resizedFile = (await resizeImage({
      file,
      maxWidth: 200,
      maxHeight: 200,
    })) as File;
    console.log('resizedFile', resizedFile);

    const formData = new FormData();
    formData.append('file', resizedFile);
    // formData.append('file', file);

    imageMutate(formData, {
      onSuccess: (res) => {
        setImageData(res);

        profileImageMutate({
          nickname: null,
          tasteMoodId: null,
          profileImageId: res.id,
        });
      },
    });

    if (inputRef.current) {
      inputRef.current.value = '';
    }
  };

  const handleDelete = () => {
    console.log('delete and set default image');
  };

  const handleAlert = () => {
    if (!isAuthor || isUploadLoading) {
      return;
    }

    const modalProps = {
      onClose: () => {
        closeModal('profileImageAlert');
      },
      onDelete: () => {
        handleDelete();
        closeModal('profileImageAlert');
      },
      onEdit: () => {
        handleImageClick();
        closeModal('profileImageAlert');
      },
    };

    openModal('profileImageAlert', modalProps);
  };

  return (
    <Wrapper onClick={handleAlert}>
      <UserImage size="l" imageUrl={userImage} isLoading={isUploadLoading} />
      <input
        ref={inputRef}
        type="file"
        accept=".png, .jpg, .jpeg"
        onChange={handleUploadImage}
      />
      {isAuthor && (
        <EditBtn>
          <EditIcon />
        </EditBtn>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  cursor: pointer;
  input {
    display: none;
  }

  img {
    width: 100px;
    height: 100px;
    min-width: 100px;
    max-width: 100px;
    min-height: 100px;
    max-height: 100px;
  }

  ${media.md} {
    img {
      width: 75px;
      height: 75px;
      min-width: 75px;
      max-width: 75px;
      min-height: 75px;
      max-height: 75px;
    }
  }
`;

const EditBtn = styled.div`
  position: absolute;
  bottom: 0;
  right: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: ${({ theme: { radius } }) => radius.half};
  background-color: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  width: 32px;
  height: 32px;
  transition: all 0.3s ease-in-out;

  &:hover,
  &:active {
    background: ${({ theme: { colors } }) => colors.bgGray200};
  }

  ${media.md} {
    width: 24px;
    height: 24px;
  }
`;
