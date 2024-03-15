import { zodResolver } from '@hookform/resolvers/zod';
import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';
import {
  useDeleteCollection,
  useDeleteFeedFromCollection,
  useEditCollection,
  useGetCollectionDetail,
} from 'service/queries/collection';
import { useToggleLikeStatus } from 'service/queries/like';
import { styled } from 'styled-components';
import { z } from 'zod';
import { media } from 'styles/mediaQuery';
import { HeroImage } from 'components/collection/detail/HeroImage';
import { StoreMoodBadge } from 'components/common/badge/StoreMoodBadge';
import { Button } from 'components/common/button/Button';
import { Dropdown } from 'components/common/dropdown/Dropdown';
import { DropdownRow } from 'components/common/dropdown/DropdownRow';
import { Share } from 'components/common/feedUserInfo/Share';
import {
  DotGhostIcon,
  HeartSmallEmpty,
  ShareIcon,
  StoreIcon,
  HeartBgIcon,
  ChatDotsIcon,
  TrashIcon,
  HeartSmallFill,
} from 'components/common/icon/icons';
import { Input } from 'components/common/input/Input';
import { InputField } from 'components/common/input/InputField';
import { useModal } from 'components/common/modal/useModal';
import { useAuthState } from 'hooks/auth/useAuth';
import { formatTimeStamp } from 'utils/formatTimeStamp';

/** TODO
 * 1. 기본 정보 수정 기능
 * 2. 글 삭제
 * 3. 좋아요 기능
 * 4. 공유하기
 * 5. 댓글 (?)
 */

const formSchema = z.object({
  title: z.string().min(3, {
    message: '제목을 3자 이상 입력해주세요',
  }),
  content: z.string().optional(),
  // private: z.boolean(),
  // moodIds: z.array(z.any()).optional(),
});

type FormSchema = z.infer<typeof formSchema>;

export const CollectionDetailPage = () => {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<FormSchema>({
    defaultValues: {
      title: '',
      content: '',
      // moodIds: [],
      // private: true,
    },
    resolver: zodResolver(formSchema),
  });

  // TODO. private 글이면 접근 못하게 해야함
  const { id } = useParams() as { id: string };
  const [isEdit, setIsEdit] = useState(false);
  const { openModal, closeModal } = useModal<'collectionAlert'>();
  const { userInfo } = useAuthState();

  const { data: collection, isLoading } = useGetCollectionDetail(id);
  const { mutate: deleteFeed } = useDeleteFeedFromCollection();
  const { mutate: likeCollection } = useToggleLikeStatus(id);
  const { mutate: deleteCollection } = useDeleteCollection(id);
  const { mutate: editCollection } = useEditCollection(id);

  useEffect(() => {
    if (!isLoading && collection) {
      reset({
        title: collection.title,
        content: collection.description,
      });
    }
  }, [isLoading, collection, reset]);

  const isMe = userInfo?.id === collection?.author.id;

  const handleDeleteFeed = (feedId: string) => {
    deleteFeed(
      { collectionId: id, feedId },
      {
        onSuccess: () => {
          closeModal('collectionAlert');
        },
      }
    );
  };

  const toggleCollectionLike = () => {
    const isLiked = collection.liked;

    likeCollection({ id, isLiked });
  };
  // console.log(userInfo, 'userInfo');
  // console.log(collection.likeCount, 'collections collection');

  const onSubmit = (value: FormSchema) => {
    const formData = {
      content: value.content ?? '',
      title: value.title,
    };
    console.log(value, 'valuevaluevaluevalue');
    console.log(formData, 'formDataformDataformDataformDataformDataformData');
    editCollection(formData);
    setIsEdit(!isEdit);
  };

  return (
    <Wrapper>
      {collection && (
        <>
          <HeroImage
            thumbnailUrl={collection.thumbnailUrl}
            author={collection.author}
          />

          <InfoWrapper>
            <Info>
              {isEdit ? (
                <>
                  <Form onSubmit={handleSubmit(onSubmit)}>
                    <div>
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
                      <Input variant="underline">
                        <Input.CenterContent>
                          <InputField
                            {...register('content')}
                            name="content"
                            placeholder="설명을 입력해주세요 (선택 사항)"
                            type="text"
                          />
                        </Input.CenterContent>
                      </Input>
                    </div>
                    <Button type="submit" size="s" backgroundColor="black">
                      수정 완료
                    </Button>
                    `
                  </Form>
                </>
              ) : (
                <>
                  <div>
                    <Title>
                      <h1>{collection.title}</h1>
                      <span>{formatTimeStamp(collection.createdAt)}</span>
                    </Title>
                    <p>{collection.description}</p>
                  </div>
                  <div>
                    {isMe && (
                      <Dropdown align="right" opener={<DotGhostIcon />}>
                        <DropdownRow onClick={() => setIsEdit(!isEdit)}>
                          수정하기
                        </DropdownRow>
                        <DropdownRow
                          onClick={() =>
                            openModal('collectionAlert', {
                              title: '현재 컬렉션을 삭제하시겠습니까?',
                              onConfirm: () => {
                                deleteCollection();
                              },
                            })
                          }
                        >
                          삭제하기
                        </DropdownRow>
                      </Dropdown>
                    )}
                  </div>
                </>
              )}
            </Info>

            {/* <Moods>
          {collection.moods.map((mood: { id: string; name: string }) => (
            <StoreMoodBadge name={mood.name} key={mood.id} />
          ))}
        </Moods> */}

            <ActionBar>
              <button onClick={toggleCollectionLike}>
                {collection.liked ? <HeartSmallFill /> : <HeartSmallEmpty />}
                <p>좋아요 {collection.likeCount}</p>
              </button>
              {/* <button>댓글</button> */}
              <Share
                type="collection"
                targetId={collection.id}
                imageUrl={
                  collection.thumbnailUrl ||
                  'https://images.unsplash.com/photo-1606787366850-de6330128bfc?q=80&w=400&h=400&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
                }
                description={collection.description}
              >
                <button>
                  <ShareIcon />
                  <p>공유하기</p>
                </button>
              </Share>
            </ActionBar>
          </InfoWrapper>

          <FeedsWrapper>
            <h2>
              피드들 <span>{collection.feeds.length}</span>
            </h2>

            <ul>
              {/* 수정되면 type 박아용 */}
              {/* eslint-disable-next-line @typescript-eslint/no-explicit-any */}
              {collection.feeds.map((feed: any) => (
                <FeedItem key={feed.id}>
                  <img src={feed.thumbnailUrl} alt="" />
                  <FeedInfo>
                    <FeedTop>
                      <FeedHeader>
                        <FeedTitle>
                          <StoreIcon /> <h3>{feed.store.name}</h3>
                        </FeedTitle>
                        {isMe && (
                          <TrashIcon
                            onClick={() =>
                              openModal('collectionAlert', {
                                title:
                                  '해당 피드를 컬렉션에서 삭제하시겠습니까?',
                                onConfirm: () => handleDeleteFeed(feed.id),
                              })
                            }
                          />
                        )}
                      </FeedHeader>
                      <p>{feed.content}</p>
                    </FeedTop>
                    <FeedBottom>
                      <FeedMoods>
                        {feed.storeMood.map((mood: Badge) => (
                          <StoreMoodBadge name={mood.name} key={mood.id} />
                        ))}
                      </FeedMoods>
                      <FeedIcon>
                        <div>
                          <HeartBgIcon />
                          <span>{feed.likeCount}</span>
                        </div>
                        <div>
                          <ChatDotsIcon />
                          <span>{feed.commentCount}</span>
                        </div>
                      </FeedIcon>
                    </FeedBottom>
                  </FeedInfo>
                </FeedItem>
              ))}
            </ul>
          </FeedsWrapper>
        </>
      )}

      {/* <div>위로 올라가기</div> */}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  max-width: 568px;
  height: 100%;
  margin: 32px 0;
  position: relative;
  left: 50%;
  background-color: ${({ theme: { colors } }) => colors.white};
  transform: translateX(-50%);
  border: 1px solid ${({ theme: { colors } }) => colors.black};

  ${media.md} {
    width: 100%;
  }

  ${media.xs} {
    border-left: none;
    border-right: none;
    margin-top: 0;
  }
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  gap: 16px;
  width: 100%;
`;

const ErrorMessage = styled.fieldset`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.pink};
`;

const InfoWrapper = styled.div`
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 16px;
`;

const Info = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Title = styled.div`
  display: flex;
  gap: 16px;
  margin-bottom: 8px;

  h1 {
    font: ${({ theme: { fonts } }) => fonts.displayB16};
  }

  span {
    font: ${({ theme: { fonts } }) => fonts.displayM14};
    color: ${({ theme: { colors } }) => colors.textSecondary};
  }
`;

// const Moods = styled.div`
//   display: flex;
//   gap: 8px;
//   margin-left: auto;
//   flex-wrap: wrap;
// `;

const ActionBar = styled.div`
  display: flex;
  justify-content: space-around;
  padding: 8px 0;
  border-top: 1px dashed black;
  border-bottom: 1px dashed black;

  button {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 8px;
    padding: 0 32px;
  }
`;

const FeedsWrapper = styled.section`
  h2 {
    font: ${({ theme: { fonts } }) => fonts.displayB16};
    margin-left: 16px;
    margin-bottom: 16px;

    span {
      color: ${({ theme: { colors } }) => colors.textSecondary};
    }
  }

  ul {
    display: flex;
    flex-direction: column;
  }
`;

const FeedInfo = styled.div`
  width: 100%;
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  justify-content: space-between;
`;

const FeedTop = styled.div`
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  gap: 8px;
`;

const FeedHeader = styled.div`
  display: flex;
  justify-content: space-between;
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  align-items: center;
`;

const FeedTitle = styled.div`
  display: flex;
  gap: 8px;
  align-items: center;
`;

const FeedItem = styled.section`
  border-top: 1px solid black;
  display: flex;
  height: 164px;

  img {
    max-width: 164px;
    max-height: 164px;
    width: 100%;
    aspect-ratio: 1/1;
  }
`;

const FeedMoods = styled.div`
  display: flex;
  gap: 8px;
  overflow: hidden;
`;

const FeedBottom = styled.div`
  display: flex;
  justify-content: space-between;

  ${media.md} {
    flex-direction: column;
    gap: 8px;
  }
`;

const FeedIcon = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;

  div {
    display: flex;
    align-items: center;
    gap: 4px;
  }
`;
