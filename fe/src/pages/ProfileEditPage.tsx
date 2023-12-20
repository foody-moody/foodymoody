import { useState } from 'react';
import { useGetTasteMood } from 'service/queries/mood';
import {
  useEditProfile,
  useGetNicknameDuplicate,
  useGetProfile,
} from 'service/queries/profile';
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
import { useInput } from 'hooks/useInput';
import { useProfileEditForm } from 'hooks/useProfileEditForm/usePofileEditForm';
import { ProfileEditSchemaType } from 'hooks/useProfileEditForm/useProfileEditSchema';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';

export const ProfileEditPage = () => {
  // 변경사항이 생겻는데 뒤로 가기 눌렀을때  alert (변경사항이 저장되지 않았습니다. 나가시겠습니까?)
  // const [isChanged, setIschanged] = useState(false);

  //  문제상황
  // 왜 새로고침시 profile데이터가 날라가는가?
  const [selectedTaste, setSelectedTaste] = useState<Mood>({
    id: '',
    name: '',
  });

  const { userInfo } = useAuthState();
  const { data: profile } = useGetProfile(userInfo.id);

  const {
    register,
    handleSubmit,
    setValue, // 어떤 값을 set시킴
    getValues, //서브밋 되고 난 이후의 값을 가져옴
    watch,
    trigger,
    errors,
    isSubmitting,
    clearErrors,
    setError,
  } = useProfileEditForm(profile);

  const {
    data: checkedNickname,
    isFetching: isDuplicateFetching,
    refetch: duplicateCheck,
  } = useGetNicknameDuplicate(watch('nickname'));

  const { data: tastes } = useGetTasteMood();
  const { mutate: profileMutate } = useEditProfile(userInfo.id);
  const isAuthor = profile?.id === userInfo.id;

  // const {
  //   value: nicknameValue,
  //   handleChange: handleNicknameChange,
  //   helperText: nicknameHelperText,
  //   isValid: isNicknameValid,
  // } = useInput({
  //   initialValue: '',
  //   validator: (value) => value.length >= 2,
  //   // nickname이 중복일때도 검증해야함
  //   helperText: '닉네임은 2자 이상 입력해주세요',
  // });
  console.log(errors, 'errors');

  console.log(watch('nickname').length);

  // const handleSubmit = () => {
  //   const registerData = {
  //     nickname: nicknameValue,
  //     tasteMoodId: selectedTaste?.id,
  //     profileImageId: '1', // 여기
  //   };
  //   console.log(registerData);

  //   //mutate
  // };

  const handleDuplicateCheck = () => {
    duplicateCheck();
  };

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedName = e.target.value;

    const selectedTaste = tastes.find(
      (taste: Mood) => taste.name === selectedName
    );
    setSelectedTaste(selectedTaste || null);
  };

  // const isFormValid = isNicknameValid && selectedTaste.id !== '';

  const onSubmit = async (data: ProfileEditSchemaType) => {
    console.log(data);

    // const editedProfileBody = {
    //   nickname: data.nickname === profile?.nickname ? null : data.nickname,
    //   // tasteMoodId: selectedTaste?.id,
    //   // profileImageId: null, // 프로필이미지는 바꾸자마자
    // };
    // profileMutate(editedProfileBody, {
    //   onError: (res) => {
    //     if (res.code === 'i001') {
    //       setError('nickname', {
    //         type: 'server',
    //         message: res.message,
    //       });
    //     }
    //   },
    // });
  };

  return (
    <Wrapper>
      <Box>
        <SectionRow>
          <Title>프로필 수정</Title>
        </SectionRow>
        {/*   서스펜스 필요 */}
        <Form onSubmit={handleSubmit(onSubmit)}>
          <Content>
            <SectionRow>
              <SubTitle>닉네임</SubTitle>
              <Row>
                <ValidatedInput
                  {...register('nickname')}
                  variant="rectangle"
                  placeholder="닉네임"
                  helperText={errors.nickname?.message}
                  //onChangeValue를 어떻게 전해주지?
                />
                <Button
                  size="l"
                  backgroundColor="orange"
                  width={170}
                  onClick={() => {
                    // async필요없나?
                    trigger('nickname');
                  }}
                  disabled={isSubmitting || watch('nickname').length < 2}
                >
                  중복검사
                  <Spinner isLoading={isDuplicateFetching} color="black" />
                </Button>
              </Row>
            </SectionRow>
            <SectionRow>
              <SubTitle>무드</SubTitle>
              <SelectLabel>
                <Select
                  value={selectedTaste?.name}
                  onChange={handleSelectChange}
                >
                  <Option value="" disabled={true}>
                    무디를 선택해주세요!
                  </Option>
                  {tastes &&
                    tastes?.map((taste: Mood) => (
                      <Option key={taste.id} value={taste.name}>
                        {taste.name}
                      </Option>
                    ))}
                </Select>
                <ArrowDownIcon />
              </SelectLabel>
            </SectionRow>

            <Row>
              <UserImageEdit
                isAuthor={isAuthor}
                imageUrl={
                  profile?.profileImageUrl ||
                  generateDefaultUserImage(userInfo.id)
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
            // onClick={handleSubmit}
            disabled={isSubmitting}
          >
            제출
            {/* <Spinner isLoading={isLoading} color="black" /> */}
          </Button>
        </Form>
      </Box>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 40px;
`;

const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 564px;
  width: 100%;
  gap: 56px;
  padding: 10px;
`;

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

const Title = styled.h1`
  font: ${({ theme: { fonts } }) => fonts.displayB24};
`;
const SubTitle = styled.h2`
  font: ${({ theme: { fonts } }) => fonts.displayM20};
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
