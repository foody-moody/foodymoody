import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import { LoginSchemaType, loginSchema } from './useLoginFormSchema';

export const useLoginForm = () => {
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
    reset,
  } = useForm<LoginSchemaType>({
    defaultValues: {
      email: '',
      password: '',
    },
    mode: 'all',
    reValidateMode: 'onChange',
    resolver: zodResolver(loginSchema),
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
    errorItem: { errors, setError },
    reset,
  };
};
