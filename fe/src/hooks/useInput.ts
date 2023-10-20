import { useState } from 'react';

type InputProps = {
  initialValue?: string;
  helperText?: string;
  validator?: (value: string) => boolean;
};

export const useInput = (props: InputProps) => {
  const [value, setValue] = useState(props.initialValue = '');
  const handleChange = (value: string) => {
    setValue(value);
  };

  const isValid = props.validator?.(value);  

  const helperText = isValid ? '' : props.helperText;

  return { value, handleChange, isValid, helperText };
};
