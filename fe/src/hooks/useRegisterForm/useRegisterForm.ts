import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import { RegisterSchemaType, registerSchema } from './useRegisterFormSchema';

export const useRegisterForm = () => {
  const {
    register,
    handleSubmit,
    formState: {
      errors,
      isSubmitting,
      isSubmitSuccessful,
      isValidating,
      isValid,
      isDirty,
    },
    setError,
    clearErrors,
    getValues,
  } = useForm<RegisterSchemaType>({
    defaultValues: {
      email: '',
      nickname: '',
      password: '',
      reconfirmPassword: '',
      tasteMoodId: '',
    },
    mode: 'onChange',
    reValidateMode: 'onChange',
    resolver: zodResolver(registerSchema),
  });

  return {
    register,
    handleSubmit,
    state: {
      isSubmitting,
      isSubmitSuccessful,
      isValidating,
      isValid,
      isDirty,
    },
    errorItem: { errors, setError, clearErrors },
    getValues,
  };
};
