import { getNicknameDuplicate } from 'service/axios/profile/profile';
import { z } from 'zod';

export type ProfileEditSchemaType = z.infer<typeof profileEditSchema>;
// infer: zod 스키마의 타입을 추론하기 위해 해당 스키마가 어떤 타입을 정의 하는지를 typescript에 알려주는 기능

const nickNameSchema = () => {
  return z
    .string()
    .min(2, '닉네임은 2~10자 사이로 입력해주세요.')
    .max(10, '닉네임은 2~10자 사이로 입력해주세요.');
};

const nickNameDuplicateCheck = async (nickname: string) => {
  const response = await getNicknameDuplicate(nickname);

  return !response.isDuplicate;
};

const tasteMoodSchema = () => {
  return z.string().min(1, '맛과 분위기를 선택해주세요.');
};

// const profileImageSchema = () => {
//   return z.string().nullable().optional();
// };

export const profileEditSchema = z
  .object({
    nickname: nickNameSchema(),
    tasteMoodId: tasteMoodSchema(),
    // profileImageId: profileImageSchema(),
  })
  .refine(
    async (value) => {
      return await nickNameDuplicateCheck(value.nickname);
    },
    {
      path: ['nickname'],
      message: '중복된 닉네임입니다.',
    }
  );

// const MAX_FILE_SIZE_BYTES = 1024 * 1024 * 2;
// const ALLOWED_TYPES = ['image/png', 'image/jpg', 'image/jpeg'];

// const RegistrationSchema = z.object({
//   profileImage: z
//     .any()
//     .refine((files) => files?.length === 1, 'Image is required.')
//     .refine(
//       (files) => files?.[0]?.size <= MAX_FILE_SIZE_BYTES,
//       '이미지는 2MB 이하의 png, jpg, jpeg 파일만 업로드 가능합니다.'
//     )
//     .refine(
//       (files) => ALLOWED_TYPES.includes(files?.[0]?.type),
//       '이미지는 2MB 이하의 png, jpg, jpeg 파일만 업로드 가능합니다.'
//     ),
// });
