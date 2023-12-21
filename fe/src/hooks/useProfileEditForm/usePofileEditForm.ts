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
    trigger,
    watch,
  } = useForm<ProfileEditSchemaType>({
    defaultValues: {
      nickname: profile?.nickname,
      tasteMoodId: profile?.tasteMoodId,
      // profileImageId: profile?.profileImageId,
    },
    mode: 'onSubmit',
    reValidateMode: 'onSubmit',
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
    errorItem: { errors, setError },
    trigger,
    watch,
  };
};
