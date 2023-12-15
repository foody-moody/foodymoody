import { Suspense } from 'react';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { NotiSkeleton } from 'components/common/skeleton/NotiSkeleton';
import { NotiList } from 'components/notification/NotiList';

export const NotiPage = () => {
  return (
    <Wrapper>
      <Buttons>
        <button>모든 알림 읽기</button>
        <button>읽은 알림 삭제</button>
      </Buttons>
      <Suspense
        fallback={
          <DeferredComponent>
            <NotiSkeleton notiCount={10} />
          </DeferredComponent>
        }
      >
        <NotiList />
      </Suspense>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  max-width: 540px;
  margin: 60px auto;

  ${media.md} {
    margin: 16px auto;
  }
`;

const Buttons = styled.div`
  display: flex;
  flex: 1 1;
  margin-bottom: 20px;
`;
