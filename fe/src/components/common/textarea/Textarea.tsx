import { styled } from 'styled-components';
import { customScrollStyle } from 'styles/customStyle';

type Props = {
  value: string;
  placeholder: string;
  limitedLength?: number;
  onChange(value: string): void;
};

export const TextArea: React.FC<Props> = ({
  value,
  placeholder,
  limitedLength = 500,
  onChange,
}) => {
  const onChangeTextarea = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const { value } = e.target;
    if (value.length > limitedLength) {
      console.log('end');

      return;
    }
    onChange(value);
  };

  return (
    <Wrapper>
      <textarea
        value={value}
        placeholder={placeholder}
        onChange={onChangeTextarea}
      />
      <Caption>
        {value.length} / {limitedLength}
      </Caption>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  border: 1px solid ${({ theme }) => theme.colors.black};
  border-radius: 0px 40px 0px 0px;
  width: 100%;
  height: 100%;
  max-height: 300px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;

  textarea {
    min-height: 150px;
    padding: 8px 16px 0px 16px;
    height: 100%;
    max-height: 300px;
    box-sizing: border-box;
    resize: none;
    border: none;
    outline: none;
    border-radius: 0px 40px 0px 0px;
    font: ${({ theme: { fonts } }) => fonts.displayM14};
    color: ${({ theme: { colors } }) => colors.textPrimary};

    &::placeholder {
      font: ${({ theme: { fonts } }) => fonts.displayM14};
      color: ${({ theme: { colors } }) => colors.textPlaceholder};
    }

    ${customScrollStyle}
  }
`;

const Caption = styled.div`
  width: 100%;
  padding: 0px 8px;
  display: flex;
  justify-content: flex-end;
  gap: 8px;
  box-sizing: border-box;
  background-color: ${({ theme: { colors } }) => colors.white};
  font: ${({ theme: { fonts } }) => fonts.displayM10};
  color: ${({ theme: { colors } }) => colors.textPlaceholder};
`;
