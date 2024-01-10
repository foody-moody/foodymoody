import { AtomEffect, atom } from 'recoil';

export const localStorageEffect: <T>(key: string) => AtomEffect<T> =
  (key: string) =>
  ({ setSelf, onSet }) => {
    const savedValue = localStorage.getItem(key);

    if (savedValue != null) {
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      return setSelf(savedValue as any); //string
    } else {
      localStorage.removeItem(key);
    }

    onSet((newValue) => {
      if (newValue != null) {
        // return localStorage.setItem(key, newValue as string);
        localStorage.setItem(key, newValue as string);
      } else {
        localStorage.removeItem(key);
      }
    });
  };

export const accessTokenState = atom<string>({
  key: 'accessTokenState',
  default: '',
  effects: [localStorageEffect('accessToken')],
});

export const refreshTokenState = atom<string>({
  key: 'refreshTokenState',
  default: '',
  effects: [localStorageEffect('refreshToken')],
});

export const userInfoState = atom<string>({
  key: 'userInfoState',
  default: '',
  effects: [localStorageEffect('userInfo')],
});
