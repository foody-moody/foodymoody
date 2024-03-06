import { zodResolver } from '@hookform/resolvers/zod';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import { useToast } from 'recoil/toast/useToast';
import {
  useAddFeedToCollection,
  useAddUserCollection,
  useDeleteFeedFromCollection,
  useGetCollectionsWithFeedStatus,
  useGetUserCollectionTitle,
} from 'service/queries/collection';
import styled from 'styled-components';
import { z } from 'zod';
import { customScrollStyle } from 'styles/customStyle';
import { StoreMoodSelector } from '../badge/StoreMoodSelector';
import { Button } from '../button/Button';
import { Checkbox } from '../checkbox/CheckBox';
import { Dim } from '../dim/Dim';
import { PlusSquareIcon } from '../icon/icons';
import { Input } from '../input/Input';
import { InputField } from '../input/InputField';
import { useModal } from './useModal';

const formSchema = z.object({
  title: z.string().min(3, {
    message: '제목을 3자 이상 입력해주세요',
  }),
  description: z.string().optional(),
  private: z.boolean(),
  moodIds: z.array(z.any()).optional(),
});

type FormSchema = z.infer<typeof formSchema>;

type MyCollection = {
  id: string;
  title: string;
};

type MyCollectionWithFeedStatus = MyCollection & { containsFeed: boolean };

export const CollectionModal: React.FC<CollectionModalProps> = ({
  type = 'default', // default, addFeed
  feedId = null,
}) => {
  const {
    //  openModal,
    closeModal,
  } = useModal<'collection'>();
  const toast = useToast();
  const [selectedBadgeList, setSelectedBadgeList] = useState<Badge[]>([]);
  const [isFormToggle, setIsFormToggle] = useState(false);
  const [isPrivate, setIsPrivate] = useState(false);

  const { data: myCollectionTitle } = useGetUserCollectionTitle(type);
  const { data: myCollectionTitleWithFeedStatus, isLoading } =
    useGetCollectionsWithFeedStatus(feedId);

  const { mutate: addFeed } = useAddFeedToCollection();
  const { mutate: deleteFeed } = useDeleteFeedFromCollection();

  const handleSelectBadgeList = (badges: Badge[]) => {
    setSelectedBadgeList(badges);
  };

  const handleCheckChange = (collectionId: string, isChecked: boolean) => {
    if (!feedId) return;

    if (isChecked) {
      addFeed({ collectionId, feedId });
    } else {
      deleteFeed({ collectionId, feedId });
    }
  };

  const handleOpenForm = () => {
    setIsFormToggle(!isFormToggle);
  };

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormSchema>({
    defaultValues: {
      title: '',
      description: '',
      moodIds: [],
      private: true,
    },
    resolver: zodResolver(formSchema),
  });
  const { mutate: addCollection } = useAddUserCollection();

  const onSubmit = async (value: FormSchema) => {
    const selectedMoodIds = selectedBadgeList.map((badge) => badge.id);

    const formData = {
      private: isPrivate,
      description: value.description ?? '',
      moodIds: selectedMoodIds,
      title: value.title,
    };

    addCollection(formData, {
      onSuccess: () => {
        toast.success('컬렉션 추가 성공');
        closeModal('collection');
      },
    });
  };

  return (
    <>
      <Dim isTransparent onClick={() => closeModal('collection')} />
      <Wrapper>
        <MyList>
          <h2>내 컬렉션 목록</h2>
          <ListBox $isFormToggle={isFormToggle}>
            {type === 'default' && (
              <>
                {myCollectionTitle && myCollectionTitle.length > 0 ? (
                  myCollectionTitle.map((collection: MyCollection) => (
                    <List key={collection.id}>
                      <Title>{collection.title}</Title>
                    </List>
                  ))
                ) : (
                  <Text>아직 회원님의 컬렉션이 없어요</Text>
                )}
              </>
            )}

            {type === 'addFeed' && isLoading && <Skeleton count={3} />}
            {type === 'addFeed' && !isLoading && (
              <>
                {myCollectionTitleWithFeedStatus &&
                myCollectionTitleWithFeedStatus.length > 0 ? (
                  myCollectionTitleWithFeedStatus.map(
                    (collection: MyCollectionWithFeedStatus) => (
                      <List key={collection.id}>
                        {!isFormToggle && (
                          <Checkbox
                            id={collection.id}
                            checked={collection.containsFeed}
                            onCheckChange={handleCheckChange}
                          />
                        )}
                        <Title>{collection.title}</Title>
                      </List>
                    )
                  )
                ) : (
                  <Text>아직 회원님의 컬렉션이 없어요</Text>
                )}
              </>
            )}
          </ListBox>
        </MyList>

        {!isFormToggle && (
          <OpenFormBtn onClick={handleOpenForm}>
            <PlusSquareIcon />
            새로운 컬렉션 만들기
          </OpenFormBtn>
        )}

        {isFormToggle && (
          <Form onSubmit={handleSubmit(onSubmit)}>
            <div>
              <SubTitle>컬렉션 제목</SubTitle>
              <Input variant="underline">
                <Input.CenterContent>
                  <InputField
                    {...register('title')}
                    name="title"
                    placeholder="제목을 입력해주세요"
                    type="text"
                  />
                </Input.CenterContent>
              </Input>
              <ErrorMessage>{errors.title?.message}</ErrorMessage>
            </div>

            <div>
              <SubTitle>컬렉션 설명 (선택사항)</SubTitle>
              <Input variant="underline">
                <Input.CenterContent>
                  <InputField
                    {...register('description')}
                    name="description"
                    placeholder="설명을 입력해주세요 (선택 사항)"
                    type="text"
                  />
                </Input.CenterContent>
              </Input>
            </div>

            <div>
              <SubTitle>컬렉션 무드 (선택사항)</SubTitle>

              <StoreMoodSelector
                selectedBadges={selectedBadgeList}
                onSelectedBadges={handleSelectBadgeList}
              />
            </div>

            <PrivacyBox>
              <Button
                size="xs"
                backgroundColor={isPrivate ? 'black' : 'white'}
                onClick={() => setIsPrivate(!isPrivate)}
                type="button"
              >
                비공개
              </Button>
              <Button
                size="xs"
                backgroundColor={isPrivate ? 'white' : 'black'}
                onClick={() => setIsPrivate(!isPrivate)}
                type="button"
              >
                공개
              </Button>
            </PrivacyBox>

            <SubmitBtn
              type="submit"
              size="s"
              shadow
              backgroundColor="blue500"
              width={120}
            >
              만들기
            </SubmitBtn>
          </Form>
        )}
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  background-color: ${({ theme: { colors } }) => colors.white};
  padding: 16px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 100;
  max-width: 375px;
  min-width: 343px;
  width: 100%;
  height: fit-content;
`;

const MyList = styled.div`
  h2 {
    font: ${({ theme: { fonts } }) => fonts.displayB20};
    margin-bottom: 4px;
  }

  border-bottom: 1px dashed black;
  padding-bottom: 8px;
`;

const Text = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;

const OpenFormBtn = styled.button`
  display: flex;
  gap: 8px;
  align-items: center;
  font: ${({ theme: { fonts } }) => fonts.displayM16};
`;

const SubTitle = styled.h3`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
  margin-bottom: 4px;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  gap: 16px;
`;

const SubmitBtn = styled(Button)`
  margin-left: auto;
`;

const Title = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  margin-bottom: 4px;
`;

const List = styled.div`
  display: flex;
  flex-direction: row;
`;

const ListBox = styled.ul<{
  $isFormToggle: boolean;
}>`
  max-height: ${({ $isFormToggle }) => ($isFormToggle ? '90px' : 'none')};
  overflow-y: ${({ $isFormToggle }) => ($isFormToggle ? 'auto' : 'none')};
  height: 100%;

  ${customScrollStyle}
`;

const PrivacyBox = styled.fieldset`
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 8px;
  > button {
    flex: 1;
  }
`;
const ErrorMessage = styled.fieldset`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.pink};
`;
