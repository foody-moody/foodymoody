import { useToggle } from 'recoil/booleanState/useToggle';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import {
  GridOffIcon,
  GridOnIcon,
  ListOffIcon,
  ListOnIcon,
} from 'components/common/icon/icons';

export const LayoutButton = () => {
  const grid = useToggle('grid');
  // const { isGrid, handleSetOn, handleSetOff } = useSetLayout();

  return (
    <Wrapper>
      <TextButton color="black" onClick={grid.toggleOn}>
        {grid.isTrue ? <GridOnIcon /> : <GridOffIcon />}
      </TextButton>
      <TextButton color="black" onClick={grid.toggleOff}>
        {grid.isTrue ? <ListOffIcon /> : <ListOnIcon />}
      </TextButton>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  gap: 4px;
  align-items: center;
`;
