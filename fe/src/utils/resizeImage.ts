import Resizer from 'react-image-file-resizer';

type Props = {
  file: File;
  maxWidth: number;
  maxHeight: number;
};

export const resizeImage = ({ file, maxWidth, maxHeight }: Props) => {
  const format = file.name.slice(file.name.lastIndexOf('.') + 1).toLowerCase();
  const compressFormat = format === 'png' ? 'png' : 'jpeg';

  return new Promise((res) => {
    Resizer.imageFileResizer(
      file,
      maxWidth,
      maxHeight,
      compressFormat,
      100,
      0,
      (uri) => {
        res(uri);
      },
      'file'
    );
  });
};
