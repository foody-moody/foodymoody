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
    setValue,
    getValues,
    watch,
    trigger,
    formState: { errors, isSubmitting, isValidating },
    clearErrors,
    setError,
  } = useForm<ProfileEditSchemaType>({
    defaultValues: {
      nickname: profile?.nickname,
    },
    mode: 'onChange',
    reValidateMode: 'onChange',
    resolver: zodResolver(profileEditSchema),
  });

  return {
    register,
    handleSubmit,
    setValue,
    getValues,
    watch,
    trigger,
    errors,
    isSubmitting,
    isValidating,
    clearErrors,
    setError,
  };
};
