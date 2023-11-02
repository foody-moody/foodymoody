import { styled } from 'styled-components';
import { v4 as uuidv4 } from 'uuid';
import { media } from 'styles/mediaQuery';
import { TextButton } from 'components/common/button/TextButton';
import { Dim } from 'components/common/dim/Dim';
import { Input } from 'components/common/input/Input';
import { MenuItemEditor } from 'components/common/menuItemEditor/MenuItemEditor';
import { useMenuItem } from 'hooks/useMenuItem';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const NewFeedModalPage = () => {
  const { navigateToHome } = usePageNavigator();

  return (
    <>
      <Dim onClick={navigateToHome} />
      <Wrapper>
        <Top>
          <TextButton color="black">취소</TextButton>
          <TextButton color="black">게시</TextButton>
        </Top>
        <Form>
          <Div>
            <Title>가게 이름을 작성해주세요</Title>
            <Input variant="underline" />
          </Div>
          <Div>
            <Title>리뷰를 작성 할 메뉴를 등록해주세요</Title>
          </Div>
        </Form>
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  padding: 16px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 100;
  border: 1px solid black;
  background-color: #fff;

  /* ${media.md} {
    max-width: 580px;
    min-width: 379px;
    width: 100%;
    max-height: 88dvh;
    height: 100%;
    overflow: hidden;
  } */
`;

const Top = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
`;

const Form = styled.div``;

const Title = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
  color: ${({ theme: { colors } }) => colors.black};
`;

const Div = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
  color: ${({ theme: { colors } }) => colors.black};
`;
