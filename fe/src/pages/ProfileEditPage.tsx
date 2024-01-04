import { Suspense } from 'react';
import { styled } from 'styled-components';
import { ProfileEditForm } from 'components/profileEdit/ProfileEditForm';

export const ProfileEditPage = () => {
  return (
    <Wrapper>
      <Box>
        <Suspense>
          <ProfileEditForm />
        </Suspense>
      </Box>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 16px;
`;

const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 564px;
  width: 100%;
  gap: 56px;
`;
