export const formatPhoneNum = (num?: string) => {
  return num?.replace(/\s+/g, '-');
};
