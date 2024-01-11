import { styled } from 'styled-components';

type Props = {
  isOn: boolean;
  onClick: () => void;
  label?: string | null;
};

export const Switch = ({ isOn, onClick, label = null }: Props) => {
  return (
    <Wrapper>
      {label && <Label>{label}</Label>}
      <SwitchWrapper $isOn={isOn} onClick={onClick}>
        <SwitchSlider $isOn={isOn} />
      </SwitchWrapper>
    </Wrapper>
  );
};

const SwitchWrapper = styled.div<{ $isOn: boolean }>`
  width: 44px;
  height: 24px;
  border-radius: 12px;
  background-color: ${({ $isOn, theme: { colors } }) =>
    $isOn ? colors.black : colors.bgGray400};
  position: relative;
  transition: background-color 0.2s;
  cursor: pointer;
`;

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Label = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
`;

const SwitchSlider = styled.div<{ $isOn: boolean }>`
  width: 16px;
  height: 16px;
  border-radius: 50%;
  background-color: #fff;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  left: ${(props) => (props.$isOn ? 'calc(100% - 20px)' : '4px')};
  transition: left 0.3s;
`;
