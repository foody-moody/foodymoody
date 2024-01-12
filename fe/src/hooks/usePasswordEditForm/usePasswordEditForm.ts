import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import {
  PasswordEditSchemaType,
  passwordEditSchema,
} from './usePasswordEditSchema';

export const usePasswordEditForm = () => {
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
  } = useForm<PasswordEditSchemaType>({
    defaultValues: {
      password: '',
      newPassword: '',
      newPasswordCheck: '',
    },
    mode: 'all',
    reValidateMode: 'onChange',
    resolver: zodResolver(passwordEditSchema),
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
  };
};
