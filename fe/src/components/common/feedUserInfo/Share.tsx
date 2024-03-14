import { useEffect } from 'react';

const { VITE_KAKAO_MAP } = import.meta.env;

type Props = {
  type: 'feed' | 'collection';
  targetId?: string;
  imageUrl?: string;
  description?: string;
  children?: React.ReactNode;
} & React.HTMLAttributes<HTMLDivElement>;

export const Share: React.FC<Props> = ({
  type,
  targetId,
  imageUrl,
  description,
  children,
  ...props
}) => {
  useEffect(() => {
    if (window.Kakao) {
      //인증이 안되어있는 경우 init
      if (!window.Kakao.isInitialized()) {
        window.Kakao.init(VITE_KAKAO_MAP);
      }
    }
  }, []);

  const baseUrl = 'https://foodymoody.site';

  const urlMaps = {
    feed: `${baseUrl}/detail/feed/${targetId}`,
    collection: `${baseUrl}/collection/${targetId}`,
  };

  const handleShare = () => {
    window.Kakao.Share.sendDefault({
      objectType: 'feed',
      content: {
        title: '푸디무디',
        description: description || `'진짜'만 모은 맛잘알들의 SNS!`, // TODO feed review로 변경
        imageUrl: imageUrl,
        link: {
          mobileWebUrl: urlMaps[type],
          webUrl: urlMaps[type],
        },
      },
      buttons: [
        {
          title: '둘러보기',
          link: {
            mobileWebUrl: urlMaps[type],
            webUrl: urlMaps[type],
          },
        },
      ],
    });
  };

  return (
    <div onClick={handleShare} {...props}>
      {children || '공유하기'}
    </div>
  ); // children
};
