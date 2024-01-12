import { styled } from 'styled-components';

type Props = {
  name: string;
  selected?: boolean;
  onSelect?: () => void;
};

export const StoreMoodBadge = ({ name, selected = false, onSelect }: Props) => {
  return (
    <Wrapper $selected={selected} onClick={onSelect}>
      # {name}
    </Wrapper>
  );
};

const Wrapper = styled.div<{
  $selected: boolean;
}>`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  width: fit-content;
  padding: 4px 12px;
  border: 1px solid;
  text-align: center;
  color: ${({ $selected, theme: { colors } }) =>
    $selected ? colors.blue100 : colors.blue500};
  background-color: ${({ $selected, theme: { colors } }) =>
    $selected ? colors.blue500 : colors.blue100};
  border-color: ${({ $selected, theme: { colors } }) =>
    $selected ? colors.blue500 : colors.blue100};
  border-radius: ${({ theme: { radius } }) => radius.small};
  padding: 3px 12px;
`;
