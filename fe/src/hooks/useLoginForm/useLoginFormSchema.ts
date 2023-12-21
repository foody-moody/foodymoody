import { z } from 'zod';

export type LoginSchemaType = z.infer<typeof loginSchema>;

const emailSchema = () => {
  return z
    .string()
    .min(1, '이메일을 입력해주세요.')
    .regex(
      /^[a-zA-Z0-9._-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$/,
      '이메일 형식에 맞게 입력해주세요.'
    );
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

export const loginSchema = z.object({
  email: emailSchema(),
  password: passwordSchema(),
});
