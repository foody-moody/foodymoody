import { z } from 'zod';

export type PasswordEditSchemaType = z.infer<typeof passwordEditSchema>;

const passwordLengthSchema = () => {
  return z.string().min(8, '비밀번호를 입력해주세요.');
};

const newPasswordSchema = () => {
  return z
    .string()
    .min(8, '영문+숫자+특수문자(! @ # $ % & * ?) 조합 8~15자리를 입력해주세요.')
    .regex(
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/,
      '영문+숫자+특수문자(! @ # $ % & * ?) 조합 8~15자리를 입력해주세요.'
    );
};

export const passwordEditSchema = z
  .object({
    password: passwordLengthSchema(),
    newPassword: newPasswordSchema(),
    newPasswordCheck: passwordLengthSchema(),
  })
  .refine((value) => value.password !== value.newPassword, {
    path: ['newPassword'],
    message: '기존 비밀번호와 동일합니다.',
  })
  .refine((value) => value.newPassword === value.newPasswordCheck, {
    path: ['newPasswordCheck'],
    message: '비밀번호가 일치하지 않습니다.',
  });
