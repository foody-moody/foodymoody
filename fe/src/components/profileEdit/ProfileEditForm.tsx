import { getNicknameDuplicate } from 'service/axios/profile/profile';
import { useGetTasteMood } from 'service/queries/mood';
import { useEditProfile, useGetProfile } from 'service/queries/profile';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import {
  FlexColumnBox,
  FlexRowBox,
} from 'components/common/feedUserInfo/FeedUserInfo';
import { ArrowDownIcon } from 'components/common/icon/icons';
import { Spinner } from 'components/common/loading/spinner';
import { UserImageEdit } from 'components/common/userImage/UserImageEdit';
import { ValidatedInput } from 'components/validatedInput/ValidatedInput';
import { useAuthState } from 'hooks/auth/useAuth';
import { useProfileEditForm } from 'hooks/useProfileEditForm/usePofileEditForm';
import { ProfileEditSchemaType } from 'hooks/useProfileEditForm/useProfileEditSchema';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';

export const ProfileEditForm = () => {
  // 변경사항이 생겻는데 어디가려고할때 alert 할지? (변경사항이 저장되지 않았습니다. 나가시겠습니까?)

  const { userInfo } = useAuthState();
  const { data: profile } = useGetProfile(userInfo.id);
  const { data: tastes } = useGetTasteMood();
  console.log(profile);

  const { mutate: profileMutate } = useEditProfile(userInfo.id);
  const { register, handleSubmit, state, errorItem, getValues } =
    useProfileEditForm(profile);

  const isAuthor = profile?.id === userInfo.id;

  const checkBtnDisabled =
    state.isValidating ||
    !!errorItem.errors.nickname ||
    getValues('nickname') === profile?.nickname;

  const submitBtnDisabled =
    state.isValidating || state.isSubmitting || !state.isDirty;

  const handleDuplicateCheck = async () => {
    const nickname = getValues('nickname');
    const res = await getNicknameDuplicate(nickname);

    if (res.duplicate) {
      errorItem.setError('nickname', {
        message: '이미 존재하는 닉네임입니다.',
      });
    } else {
      errorItem.setError('nickname', {
        message: '사용할 수 있는 닉네임입니다.',
        type: 'available',
      });
      // errorItem.clearErrors('nickname');
    }
  };

  const onSubmit = async (value: ProfileEditSchemaType) => {
    const registerData = {
      nickname: value.nickname === profile?.nickname ? null : value.nickname,
      tasteMoodId:
        value.tasteMoodId === profile?.tasteMoodId ? null : value.tasteMoodId,
      profileImageId: null,
    };

    profileMutate(registerData, {
      onError: (error) => {
        if (error.response?.data.code === 'm003') {
          errorItem.setError(
            'nickname',
            {
              message: error.response?.data.message,
            },
            { shouldFocus: true }
          );
        }
      },
    });
  };

  return (
    <Form onSubmit={handleSubmit(onSubmit)}>
      <Content>
        <SectionRow>
          <SubTitle>닉네임</SubTitle>
          <Row>
            <ValidatedInput
              {...register('nickname')}
              variant="rectangle"
              helperText={errorItem.errors.nickname?.message}
              helperType={
                errorItem.errors.nickname?.type === 'available'
                  ? 'success'
                  : 'error'
              }
            />
            <Button
              type="button"
              size="l"
              backgroundColor="orange"
              width={170}
              disabled={checkBtnDisabled}
              onClick={handleDuplicateCheck}
            >
              중복검사
            </Button>
          </Row>
        </SectionRow>
        <SectionRow>
          <SubTitle>무드</SubTitle>
          <SelectLabel>
            <Select
              {...register('tasteMoodId')}
              // value={selectedTaste?.name}
              // onChange={handleSelectChange}
            >
              {tastes &&
                tastes?.map((taste: Badge) => (
                  <Option key={taste.id} value={taste.id}>
                    {taste.name}
                  </Option>
                ))}
            </Select>
            <ArrowDownIcon />
          </SelectLabel>
          {errorItem.errors.tasteMoodId?.message && (
            <p>{errorItem.errors.tasteMoodId?.message}</p>
          )}
        </SectionRow>

        <Row>
          <UserImageEdit
            isAuthor={isAuthor}
            imageUrl={
              profile?.profileImageUrl || generateDefaultUserImage(userInfo.id)
            }
          />
          <InfoMessage>
            프로필 사진을 변경할 수 있어요! <br />
            사진은 2MB 이하의 JPG, PNG 파일로 업로드해주세요.
          </InfoMessage>
        </Row>
      </Content>
      <Button
        type="submit"
        size="l"
        backgroundColor="orange"
        disabled={submitBtnDisabled}
      >
        제출
        <Spinner isLoading={state.isSubmitting} color="black" />
      </Button>
    </Form>
  );
};

const Form = styled.form`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 56px;
`;

const Content = styled(FlexColumnBox)`
  width: 100%;
  gap: 32px;
`;

const SectionRow = styled(FlexColumnBox)`
  width: 100%;
  gap: 8px;
`;

const Row = styled(FlexRowBox)`
  width: 100%;
  align-items: center;
  gap: 8px;
`;

const SubTitle = styled.h2`
  font: ${({ theme: { fonts } }) => fonts.displayB20};
`;

const InfoMessage = styled.p`
  margin-left: 20px;
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  color: ${({ theme: { colors } }) => colors.textPrimary};
`;

const SelectLabel = styled.label`
  position: relative;

  svg {
    position: absolute;
    right: 8px;
    top: 50%;
    transform: translateY(-50%);
  }
`;

const Select = styled.select`
  -webkit-appearance: none;
  padding: 14px 40px 14px 12px;
  width: 100%;
  border: 1px solid ${({ theme: { colors } }) => colors.black};

  border-radius: 5px;
  background: ${({ theme: { colors } }) => colors.white};
  cursor: pointer;
  font: ${({ theme: { fonts } }) => fonts.displayM14};

  &:focus {
    outline: none;
    border-color: ${({ theme: { colors } }) => colors.textTertiary};
  }
`;

const Option = styled.option`
  &[value=''][disabled] {
    display: none;
  }
`;
