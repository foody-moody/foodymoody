import { css } from 'styled-components';
import IBMPlexSansMedium from 'assets/font/IBMPlexSansKR-Medium.woff';
import IBMPlexSansRegular from 'assets/font/IBMPlexSansKR-Regular.woff';
import IBMPlexSansSemiBold from 'assets/font/IBMPlexSansKR-SemiBold.woff';

export const fonts = css`
  @font-face {
    font-family: 'IBM Plex Sans KR';
    font-style: normal;
    font-weight: normal;
    font-display: optional;
    src:
      local('IBMPlexSansKR'),
      url(${IBMPlexSansRegular}) format('woff');
  }

  @font-face {
    font-family: 'IBM Plex Sans KR';
    font-style: normal;
    font-weight: 400;
    font-display: optional;
    src:
      local('IBMPlexSansKR'),
      url(${IBMPlexSansMedium}) format('woff');
  }

  @font-face {
    font-family: 'IBM Plex Sans KR';
    font-style: normal;
    font-weight: 600;
    font-display: optional;
    src:
      local('IBMPlexSansKR'),
      url(${IBMPlexSansSemiBold}) format('woff');
  }
`;
