import EmojiPicker, { EmojiStyle, EmojiClickData } from 'emoji-picker-react';
import { InputHTMLAttributes, useState } from 'react';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { FaceIcon } from 'components/common/icon/icons';
import { Input2 } from 'components/common/input/Input2';
import { InputField } from 'components/common/input/InputField';

type Props = {
  value?: string;
  limitedLength?: number;
  onChangeValue?(value: string): void;
  onSubmitComment?(): void;
} & InputHTMLAttributes<HTMLInputElement>;

export const CommentInputContainer: React.FC<Props> = ({
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
      <Input2 variant="comment">
        <Input2.TopPanel>
          {isEmojiVisible && (
            <EmojiPicker
              emojiStyle={EmojiStyle.NATIVE}
              onEmojiClick={handleEmojiClick}
            />
          )}
        </Input2.TopPanel>
        <Input2.LeftContent>
          <FaceIcon onClick={handleEmojiOpen} />
        </Input2.LeftContent>
        <Input2.CenterContent>
          <InputField
            placeholder="댓글달기"
            limitedLength={limitedLength}
            value={value}
            onChangeValue={onChangeValue}
            onPressEnter={onSubmitComment}
            {...props}
          />
        </Input2.CenterContent>
        <Input2.RightContent>
          <TextButton color="orange" size="m" onClick={onSubmitComment}>
            게시
          </TextButton>
        </Input2.RightContent>
      </Input2>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
`;
