import { useState } from 'react';

export const useReadMore = (text: string, limit: number = 80) => {
  const [readMore, setReadMore] = useState(false);

  const displayText = readMore ? text : `${text.substring(0, limit)}...`;

  const toggleReadMore = () => {
    setReadMore(!readMore);
  };

  return { displayText, toggleReadMore, readMore };
};
