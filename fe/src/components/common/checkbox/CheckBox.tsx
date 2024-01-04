import { useState } from 'react';
import styled from 'styled-components';

type Props = {
  id: string;
  onCheckChange: (id: string, checkStatus: boolean) => void;
  checked?: boolean;
};

export const Checkbox: React.FC<Props> = ({
  id,
  checked = false,
  onCheckChange,
}) => {
  const [isChecked, setIsChecked] = useState(checked);

  const toggleCheckboxChange = () => {
    const checkStatus = !isChecked;
    setIsChecked(!isChecked);
    onCheckChange(id, checkStatus);
  };

  return (
    <CheckboxContainer>
      <HiddenCheckbox checked={isChecked} onChange={toggleCheckboxChange} />
      <StyledCheckbox checked={isChecked} onClick={toggleCheckboxChange}>
        <Icon viewBox="0 0 24 24">
          <polyline points="17 9 11 15 7 12" />
        </Icon>
      </StyledCheckbox>
    </CheckboxContainer>
  );
};

const CheckboxContainer = styled.div`
  display: flex;
  vertical-align: middle;
  align-items: center;
  height: 24px;
  margin-right: 8px;
`;

const HiddenCheckbox = styled.input.attrs({ type: 'checkbox' })`
  border: 0;
  clip: rect(0 0 0 0);
  clippath: inset(50%);
  height: 1px;
  margin: -1px;
  overflow: hidden;
  padding: 0;
  position: absolute;
  white-space: nowrap;
  width: 1px;
`;

const Icon = styled.svg`
  display: flex;
  vertical-align: middle;
  align-items: center;
  fill: none;
  stroke: black;
  stroke-width: 2px;
  stroke-linecap: round;
  stroke-linejoin: round;
`;

const StyledCheckbox = styled.div<{
  checked: boolean;
}>`
  display: inline-block;
  border: 1.5px solid ${({ theme: { colors } }) => colors.black};

  width: 18px;
  height: 18px;
  background-color: ${({ checked, theme: { colors } }) =>
    checked ? colors.primary300 : colors.white};
  border-radius: 3px;
  transition: all 150ms;

  ${Icon} {
    visibility: ${({ checked }) => (checked ? 'visible' : 'hidden')};
  }
`;
