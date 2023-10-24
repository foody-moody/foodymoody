import { styled } from 'styled-components';

type Props = {
  children: React.ReactNode;
  onClick?(): void;
};

export const DropdownRow: React.FC<Props> = ({  children, onClick }) => {
  const handleClick = () => {
    onClick?.();
  }

  return <Wrapper onClick={handleClick}>{children}</Wrapper>;
};

const Wrapper = styled.li`
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  padding: 16px;

  &:hover {
    background: ${({ theme: { colors } }) => colors.bgGray200};
  }
`;
