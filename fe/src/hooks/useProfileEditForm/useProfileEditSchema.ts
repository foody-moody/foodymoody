import { getNicknameDuplicate } from 'service/axios/profile/profile';
import { z } from 'zod';

export type ProfileEditSchemaType = z.infer<typeof profileEditSchema>;
// infer: zod 스키마의 타입을 추론하기 위해 해당 스키마가 어떤 타입을 정의 하는지를 typescript에 알려주는 기능

const nickNameSchema = () => {
  return z.string().min(2, '닉네임 2자 이상 입력해주세요');
};

const nickNameDuplicateCheck = async (nickname: string) => {
  const response = await getNicknameDuplicate(nickname);
  return !response.isDuplicate;
};

// const tasteMoodIdSchema = () => {
//   return z.string();
// }

export const profileEditSchema = z
  .object({
    nickname: nickNameSchema(),
    // tasteMoodId:
  })
  .refine(
    async (value) => {
      nickNameDuplicateCheck(value.nickname);
    },
    {
      path: ['nickname'],
      message: '중복된 닉네임입니다.',
    }
  );

// .refine((data) => data.password === data.passwordCheck, {
//   path: ['passwordCheck'],
//   message: '비밀번호가 일치하지 않습니다.',
// })

// .object({
//   nickname: z.string().min(2, '닉네임을 입력해주세요'),
//   password: z
//     .string()
//     .min(8, '비밀번호를 입력해주세요.')
//     .regex(
//       /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/,
//       '영문+숫자+특수문자(! @ # $ % & * ?) 조합 8~15자리를 입력해주세요.'
//     ),
//   passwordCheck: z.string().min(8, '비밀번호를 입력해주세요.'),
// })
