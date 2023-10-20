import { useState } from 'react';

export const useInput = (initialValue: string = '') => {
  const [value, setValue] = useState(initialValue);
  const handleChange = (value: string) => {
    setValue(value);
  };

  return { value, handleChange };
};

type InputProps = {
  initialValue?: string;
  helperText?: string;
  validator?: (value: string) => boolean;
};

export const useValidateInput = (props: InputProps) => {
  const [value, setValue] = useState(props.initialValue = '');
  const handleChange = (value: string) => {
    setValue(value);
  };

  const isValid = props.validator?.(value);
  console.log(isValid , 'isValid');
  

  const helperText = isValid ? '' : props.helperText;

  return { value, handleChange, isValid, helperText };
};
