import EmojiPicker, { EmojiStyle, EmojiClickData } from 'emoji-picker-react';
import { useState, InputHTMLAttributes } from 'react';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { TextButton } from 'components/common/button/TextButton';
import { FaceIcon } from '../icon/icons';
import { InputField } from './InputField';

type Props = {
  value?: string;
  limitedLength?: number;
  onChangeValue?(value: string): void;
  onSubmitComment?(): void;
} & InputHTMLAttributes<HTMLInputElement>;

export const CommentInput: React.FC<Props> = ({
  value,
  limitedLength,
  onChangeValue,
  onSubmitComment,
  ...props
}) => {
  const [isEmojiVisible, setIsEmojiVisible] = useState(false);

  const handleEmojiOpen = () => {
    setIsEmojiVisible(!isEmojiVisible);
  };

  const handleEmojiClick = (emojiData: EmojiClickData) => {
    onChangeValue?.(value + emojiData.emoji);
    setIsEmojiVisible(false);
  };

  return (
    <Wrapper>
      {isEmojiVisible && (
        <EmojiPicker
          emojiStyle={EmojiStyle.NATIVE}
          onEmojiClick={handleEmojiClick}
        />
      )}

      <CommentWrapper>
        {<FaceIcon onClick={handleEmojiOpen} />}
        <InputField
          placeholder="댓글달기"
          limitedLength={limitedLength}
          value={value}
          onChangeValue={onChangeValue}
          onPressEnter={onSubmitComment}
          {...props}
        />
        <TextButton color="orange" size="s" onClick={onSubmitComment}>
          게시
        </TextButton>
      </CommentWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;

  aside.EmojiPickerReact {
    position: absolute;
    top: -280px;
    left: 20px;
    width: 300px !important;
    height: 270px !important;

    ${media.md} {
      width: 450px !important;
      height: 300px !important;
      top: -310px;
    }

    ${media.sm} {
      width: 470px !important;
      height: 350px !important;
      top: -360px;
    }

    ${media.xs} {
      width: 314px !important;
      height: 350px !important;
      top: -360px;
      left: 0px;
    }

    .Flex {
      display: none;
    }
  }
`;

const CommentWrapper = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px 20px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: ${({ theme: { radius } }) => radius.large};
  width: 100%;
  position: relative;
  box-sizing: border-box;
  background-color: ${({ theme: { colors } }) => colors.white};

  svg {
    cursor: pointer;
  }
`;
