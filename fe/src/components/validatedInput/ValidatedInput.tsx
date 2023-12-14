import { forwardRef, useState } from 'react';
import { styled } from 'styled-components';
import { EyeInvisible, EyeVisble } from 'components/common/icon/icons';
import { Input } from 'components/common/input/Input';
import { InputField } from 'components/common/input/InputField';

type Props = {
  type?: 'password' | 'text';
  value?: string;
  placeholder?: string;
  helperText?: string;
  nextRef?: React.RefObject<HTMLInputElement | HTMLButtonElement>;
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
      nextRef,
      ...props
    },
    ref
  ) => {
    const [isFocused, setIsFocused] = useState(false);
    const [showPassword, setShowPassword] = useState(false);

    const handleEnterPress = (
      nextRef?: React.RefObject<HTMLInputElement | HTMLButtonElement>
    ) => {
      nextRef?.current?.focus();
    };

    const handleToggleType = () => {
      setShowPassword(!showPassword);
    };

    return (
      <Wrapper>
        <Input variant="default" isFocused={isFocused} helperText={helperText}>
          <Input.InnerLabel isFocused={isFocused}>
            {placeholder}
          </Input.InnerLabel>
          <Input.CenterContent>
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
                handleEnterPress(nextRef);
              }}
              onInputFocus={() => {
                setIsFocused(true);
              }}
              {...props}
            />
          </Input.CenterContent>
          <Input.HelperText isFocused={isFocused} isError={!!helperText}>
            {helperText}
          </Input.HelperText>
          {type === 'password' && (
            <Input.RightContent>
              {showPassword ? (
                <EyeVisble onClick={handleToggleType} />
              ) : (
                <EyeInvisible onClick={handleToggleType} />
              )}
            </Input.RightContent>
          )}
        </Input>
      </Wrapper>
    );
  }
);

const Wrapper = styled.div`
  width: 100%;
`;
