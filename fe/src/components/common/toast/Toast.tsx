import React from 'react';
import ReactDOM from 'react-dom';
import { useRecoilValue } from 'recoil';
import { toastListState } from 'recoil/toast/atom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { ToastErrorIcon, ToastNotiIcon, ToastSuccessIcon } from '../icon/icons';

const toastIcons = {
  noti: <ToastNotiIcon />,
  success: <ToastSuccessIcon />,
  error: <ToastErrorIcon />,
};

export const GlobalToasts: React.FC = () => {
  const toasts = useRecoilValue(toastListState);

  return ReactDOM.createPortal(
    <Wrapper>
      {toasts.map((toast: ToastItem) => (
        <Toast
          key={toast.id}
          $toastType={toast.type}
          $isVisible={toast.isVisible}
        >
          {toastIcons[toast.type]}
          <p>{toast.message}</p>
        </Toast>
      ))}
    </Wrapper>,
    document.body
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  position: fixed;
  bottom: 4px;
  left: 4px;
  z-index: 1000;
  max-width: 17vw;
  min-width: 200px;
  width: 100%;
  ${media.xs} {
    bottom: 64px;
    min-width: 170px;
  }
`;

const Toast = styled.div<{
  $toastType: ToastType;
  $isVisible: boolean;
}>`
  display: flex;
  align-items: center;
  padding: 8px 12px;
  margin-top: 4px;
  gap: 8px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  background-color: ${({ theme: { colors } }) => colors.white};
  opacity: ${({ $isVisible }) => ($isVisible ? 1 : 0)};
  transform: translateY(${({ $isVisible }) => ($isVisible ? '0' : '20px')});
  transition:
    opacity 0.5s,
    transform 0.5s;
  width: 100%;

  svg {
    flex-shrink: 0;
    width: 18px;
  }

  p {
    flex-grow: 1;
    width: 100%;
    padding-right: 24px;
    white-space: normal;
    overflow-wrap: break-word;
    font: ${({ theme: { fonts } }) => fonts.displayM12};
  }

  ${media.xs} {
    font: ${({ theme: { fonts } }) => fonts.displayM12};
    padding: 8px;
  }
`;
