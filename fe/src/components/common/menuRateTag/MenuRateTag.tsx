import { styled } from 'styled-components';
import { StarSmallIcon } from '../icon/icons';

type Props = {
  menu: { name: string; rating: number };
};

export const MenuRateTag: React.FC<Props> = ({ menu }) => {
  return (
    <Wrapper>
      {menu.name}
      <Rating>
        <StarSmallIcon /> {menu.rating}
      </Rating>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.white};
  background-color: ${({ theme: { colors } }) => colors.dimDark};
  border-radius: ${({ theme: { radius } }) => radius.large};
  display: flex;
  justify-content: center;
  width: fit-content;
  align-items: center;
  padding: 8px 12px;
  gap: 8px;
  position: absolute;
  right: 16px;
  bottom: 16px;
`;

const Rating = styled.span`
  display: flex;
  align-items: center;
  gap: 4px;
`;
