import { styled } from 'styled-components';

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
    if (value.length > limitedLength) return;
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
  overflow: hidden;

  display: flex;
  flex-direction: column;
  justify-content: space-between;

  textarea {
    min-height: 150px;
    padding: 8px 16px 0px 16px;
    resize: none;
    border: none;
    outline: none;
    border-radius: 0px 40px 0px 0px;

    font: ${({ theme: { fonts } }) => fonts.displayM14};
    color: ${({ theme: { colors } }) => colors.textPrimary};

    &::-webkit-scrollbar {
      width: 10px;
      background-color: transparent;

      &-button {
        width: 0;
        height: 0;
      }

      &-thumb {
        width: 4px;
        border-radius: 10px;
        background-color: ${({ theme: { colors } }) => colors.textTertiary};
        border: 3px solid ${({ theme: { colors } }) => colors.white};
      }

      &-track {
        background-color: transparent;
      }
    }

    &::placeholder {
      font: ${({ theme: { fonts } }) => fonts.displayM14};
      color: ${({ theme: { colors } }) => colors.textPlaceholder};
    }
  }
`;

const Caption = styled.div`
  width: 100%;
  padding: 0px 8px;
  display: flex;
  justify-content: flex-end;
  gap: 8px;
  box-sizing: border-box;
  font: ${({ theme: { fonts } }) => fonts.displayM10};
  color: ${({ theme: { colors } }) => colors.textPlaceholder};
`;
