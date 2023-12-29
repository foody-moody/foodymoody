import { useSetLayout } from 'recoil/booleanState/useSetLayout';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import {
  GridOffIcon,
  GridOnIcon,
  ListOffIcon,
  ListOnIcon,
} from 'components/common/icon/icons';

export const LayoutButton = () => {
  const { isGrid, handleSetOn, handleSetOff } = useSetLayout();

  return (
    <Wrapper>
      <TextButton color="black" onClick={handleSetOn}>
        {isGrid ? <GridOnIcon /> : <GridOffIcon />}
      </TextButton>
      <TextButton color="black" onClick={handleSetOff}>
        {isGrid ? <ListOffIcon /> : <ListOnIcon />}
      </TextButton>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  gap: 4px;
  align-items: center;
`;
