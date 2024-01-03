import { useState } from 'react';

export const useReadMore = (text: string, limit: number = 30) => {
  const [readMore, setReadMore] = useState(false);

  const isLongText = text.length > limit;
  const displayText = readMore ? text : `${text.substring(0, limit)}`;

  const toggleReadMore = () => {
    setReadMore(!readMore);
  };

  return { displayText, toggleReadMore, readMore, isLongText };
};
