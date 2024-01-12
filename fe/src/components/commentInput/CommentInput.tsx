import EmojiPicker, { EmojiStyle, EmojiClickData } from 'emoji-picker-react';
import { InputHTMLAttributes, useState } from 'react';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { FaceIcon } from 'components/common/icon/icons';
import { Input } from 'components/common/input/Input';
import { InputField } from 'components/common/input/InputField';

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
      <Input variant="comment">
        <Input.TopPanel isOpen={isEmojiVisible}>
          <EmojiPicker
            emojiStyle={EmojiStyle.NATIVE}
            onEmojiClick={handleEmojiClick}
          />
        </Input.TopPanel>
        <Input.LeftContent>
          <FaceIcon onClick={handleEmojiOpen} />
        </Input.LeftContent>
        <Input.CenterContent>
          <InputField
            placeholder="댓글달기"
            limitedLength={limitedLength}
            value={value}
            onChangeValue={onChangeValue}
            onPressEnter={onSubmitComment}
            {...props}
          />
        </Input.CenterContent>
        <Input.RightContent>
          <TextButton color="orange" size="m" onClick={onSubmitComment}>
            게시
          </TextButton>
        </Input.RightContent>
      </Input>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
`;
