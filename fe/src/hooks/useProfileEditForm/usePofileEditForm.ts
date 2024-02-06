import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import {
  ProfileEditSchemaType,
  profileEditSchema,
} from './useProfileEditSchema';

export const useProfileEditForm = (profile?: ProfileMemberInfo) => {
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
  } = useForm<ProfileEditSchemaType>({
    defaultValues: {
      nickname: profile?.nickname,
      tasteMoodId: profile?.tasteMood.id, //
    },
    mode: 'onChange',
    reValidateMode: 'onChange',
    resolver: zodResolver(profileEditSchema),
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
