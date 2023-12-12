import { forwardRef, useState } from 'react';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { Input2 } from 'components/common/input/Input2';
import { InputField } from 'components/common/input/InputField';

type Props = {
  type?: 'password' | 'text';
  value?: string;
  placeholder?: string;
  helperText?: string;
  nextInputRef?: React.RefObject<HTMLInputElement>;
  onChangeValue?(value: string): void;
};

export const ValidatedInput = forwardRef<HTMLInputElement, Props>(
  (
    {
      type = 'text',
      value,
      placeholder,
      helperText,
      onChangeValue,
      nextInputRef,
      ...props
    },
    ref
  ) => {
    const [isFocused, setIsFocused] = useState(false);
    const [showPassword, setShowPassword] = useState(false);

    const handleEnterPress = (
      nextInputRef?: React.RefObject<HTMLInputElement>
    ) => {
      nextInputRef?.current?.focus();
    };

    const handleToggleType = () => {
      setShowPassword(!showPassword);
    };

    return (
      <Wrapper>
        <Input2 variant="default" isFocused={isFocused} helperText={helperText}>
          <Input2.InnerLabel isFocused={isFocused}>
            {placeholder}
          </Input2.InnerLabel>
          <Input2.CenterContent>
            <InputField
              ref={ref}
              type={
                type === 'password'
                  ? showPassword
                    ? 'text'
                    : 'password'
                  : 'text'
              }
              value={value}
              onChangeValue={onChangeValue}
              onPressEnter={() => {
                handleEnterPress(nextInputRef);
              }}
              onInputFocus={() => {
                setIsFocused(true);
              }}
              {...props}
            />
          </Input2.CenterContent>
          <Input2.HelperText isFocused={isFocused} isError={!!helperText}>
            {helperText}
          </Input2.HelperText>
          {type === 'password' && (
            <Input2.RightContent>
              {showPassword ? (
                <TextButton color="orange" size="m" onClick={handleToggleType}>
                  눈감아
                </TextButton>
              ) : (
                <TextButton color="black" size="m" onClick={handleToggleType}>
                  눈떠
                </TextButton>
              )}
            </Input2.RightContent>
          )}
        </Input2>
      </Wrapper>
    );
  }
);

const Wrapper = styled.div`
  width: 100%;
`;
