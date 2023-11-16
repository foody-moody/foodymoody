import { DefaultTheme } from 'styled-components';

const fonts = {
  displayB24: '600 24px/36px IBM Plex Sans KR, sans-serif',
  displayB20: '600 20px/32px IBM Plex Sans KR, sans-serif',
  displayB16: '600 16px/24px IBM Plex Sans KR, sans-serif',
  displayB14: '600 14px/16px IBM Plex Sans KR, sans-serif',
  displayB12: '600 12px/16px IBM Plex Sans KR, sans-serif',

  displayM20: '400 20px/36px IBM Plex Sans KR, sans-serif',
  displayM16: '400 16px/32px IBM Plex Sans KR, sans-serif',
  displayM14: '400 14px/24px IBM Plex Sans KR, sans-serif',
  displayM12: '400 12px/16px IBM Plex Sans KR, sans-serif',
  displayM10: '400 10px/16px IBM Plex Sans KR, sans-serif',

  selectedB20: '600 20px/32px IBM Plex Sans KR, sans-serif',
  selectedB16: '600 16px/24px IBM Plex Sans KR, sans-serif',
  selectedB12: '600 12px/16px IBM Plex Sans KR, sans-serif',
  selectedB10: '600 10px/16px IBM Plex Sans KR, sans-serif',

  availableM20: '400 20px/32px IBM Plex Sans KR, sans-serif',
  availableM16: '400 16px/24px IBM Plex Sans KR, sans-serif',
  availableM12: '400 12px/16px IBM Plex Sans KR, sans-serif',
  availableM10: '400 10px/16px IBM Plex Sans KR, sans-serif',
};

const colors = {
  bgGray50: 'rgba(166, 166, 166, 0.08)',
  bgGray100: '#FFF9F4',
  bgGray200: '#F3EBE2',
  bgGray400: '#E2DCD9',
  blue100: '#d1fafe',
  blue500: '#1ea8ed',
  yellow100: '#fdf4d7',
  yellow500: '#E9A13B',
  white: '#FFFFFF',
  red: '#A72826',
  green: '#62B273',
  black: '#0a0a0a',
  orange: '#e5855d',
  pink: '#E081AD',
  textPrimary: '#0a0a0a',
  textSecondary: '#7A7A7A',
  textTertiary: '#C2C2C2',
  textDisabled: '#474747',
  textPlaceholder: '#707070',
  dimLight: 'rgba(10, 10, 10, 0.27)',
  dimDark: 'rgba(17, 19, 24, 0.55)',
};

const radius = {
  small: '8px',
  large: '40px',
  half: '50%',
};

export const theme: DefaultTheme = {
  fonts,
  colors,
  radius,
};

export type ColorsTypes = typeof colors;
export type FontsTypes = typeof fonts;
export type RadiusTypes = typeof radius;
