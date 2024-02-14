import { z } from 'zod';

export type RegisterSchemaType = z.infer<typeof registerSchema>;

const emailSchema = () => {
  return z
    .string()
    .min(1, '이메일을 입력해주세요.')
    .regex(
      /^[a-zA-Z0-9._-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$/,
      '이메일 형식에 맞게 입력해주세요.'
    );
};

const nickNameSchema = () => {
  return z
    .string()
    .min(2, '닉네임은 2~10자 사이로 입력해주세요.')
    .max(10, '닉네임은 2~10자 사이로 입력해주세요.');
};

const passwordLengthSchema = () => {
  return z.string().min(8, '비밀번호를 입력해주세요.');
};

const passwordSchema = () => {
  return z
    .string()
    .min(8, '영문+숫자+특수문자(! @ # $ % & * ?) 조합 8~15자리를 입력해주세요.')
    .regex(
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/,
      '영문+숫자+특수문자(! @ # $ % & * ?) 조합 8~15자리를 입력해주세요.'
    );
};

const tasteMoodSchema = () => {
  return z.string().min(1, '맛과 분위기를 선택해주세요.');
};

export const registerSchema = z
  .object({
    email: emailSchema(),
    nickname: nickNameSchema(),
    password: passwordSchema(),
    repeatPassword: passwordLengthSchema(),
    tasteMoodId: tasteMoodSchema(),
  })
  .refine((value) => value.password === value.repeatPassword, {
    path: ['repeatPassword'],
    message: '비밀번호가 일치하지 않습니다.',
  });
