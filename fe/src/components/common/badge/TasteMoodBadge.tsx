import { styled } from 'styled-components';

type Props = {
  name: string;
};

export const TasteMoodBadge: React.FC<Props> = ({ name }) => {
  return <Wrapper>{name}</Wrapper>;
};

const Wrapper = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  width: fit-content;
  padding: 4px 12px;
  border: 1px solid;
  text-align: center;
  color: ${({ theme: { colors } }) => colors.yellow500};
  background-color: ${({ theme: { colors } }) => colors.white};
  border-color: ${({ theme: { colors } }) => colors.yellow500};
  border-radius: ${({ theme: { radius } }) => radius.large};
  padding: 4px 12px;
`;
