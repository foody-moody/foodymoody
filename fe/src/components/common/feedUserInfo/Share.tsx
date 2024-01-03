import { useEffect } from 'react';

const { MODE, VITE_API_URL, VITE_KAKAO_MAP } = import.meta.env;

type Props = {
  targetId?: string;
  imageUrl?: string;
};

export const Share: React.FC<Props> = ({ targetId, imageUrl }) => {
  useEffect(() => {
    if (window.Kakao) {
      //인증이 안되어있는 경우 init
      if (!window.Kakao.isInitialized()) {
        window.Kakao.init(VITE_KAKAO_MAP);
      }
    }
  }, []);

  // 링크 상수화
  const baseUrl =
    MODE === 'development' ? 'http://localhost:5173' : VITE_API_URL;

  const handleShare = () => {
    window.Kakao.Share.sendDefault({
      objectType: 'feed',
      content: {
        title: '푸디무디',
        description: `'진짜'만 모은 맛잘알들의 SNS!`, // TODO feed review로 변경
        imageUrl: imageUrl,
        link: {
          mobileWebUrl: `${baseUrl}/detail/feed/${targetId}`,
          webUrl: `${baseUrl}/detail/feed/${targetId}`,
        },
      },
      buttons: [
        {
          title: '둘러보기',
          link: {
            mobileWebUrl: `${baseUrl}/detail/feed/${targetId}`,
            webUrl: `${baseUrl}/detail/feed/${targetId}`,
          },
        },
      ],
    });
  };

  return <div onClick={handleShare}>공유하기</div>;
};