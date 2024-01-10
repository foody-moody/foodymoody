import { AtomEffect, atom } from 'recoil';

export const localStorageEffect: <T>(key: string) => AtomEffect<T> =
  (key: string) =>
  ({ setSelf, onSet }) => {
    const savedValue = localStorage.getItem(key);

    if (savedValue != null) {
      if (key === 'userInfo') {
        return setSelf(JSON.parse(savedValue));
      } else {
        return setSelf(savedValue);
      }
    }

    onSet((newValue) => {
      if (newValue != null) {
        return localStorage.setItem(key, newValue);
      } else {
        localStorage.removeItem(key);
      }
    });
  };

export const accessTokenState = atom<string | null>({
  key: 'accessTokenState',
  default: null,
  effects: [localStorageEffect('accessToken')],
});

export const refreshTokenState = atom<string | null>({
  key: 'refreshTokenState',
  default: null,
  effects: [localStorageEffect('refreshToken')],
});

export const userInfoState = atom<string | null>({
  key: 'userInfoState',
  default: null,
  effects: [localStorageEffect('userInfo')],
});
