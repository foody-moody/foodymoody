export const generateDefaultUserImage = (userId?: string) => {
  return `https://api.dicebear.com/9.x/shapes/svg?seed=${userId}`;
};
