import { useEffect } from 'react';

const { MODE, VITE_API_URL } = import.meta.env;

type Props = {
  targetId?: string;
  imageUrl?: string;
};

export const Share: React.FC<Props> = ({ targetId, imageUrl }) => {
  useEffect(() => {
    // init 해주기 전에 clean up 을 해준다.
    // window.Kakao.cleanup();

    if (window.Kakao) {
      //인증이 안되어있는 경우 인증한다.
      if (!window.Kakao.isInitialized()) {
        window.Kakao.init('196c5208e381632051ffe916af88cca1');
      }
    }
  }, []);

  // const handleShare = () => {
  //   window.Kakao.Share.sendCustom({
  //     templateId: 102388,
  //     templateArgs: {
  //       THU: imageUrl,
  //     },
  //   });
  // };

  const target =
    MODE === 'development' ? 'http://localhost:5173' : VITE_API_URL;

  const handleShare = () => {
    window.Kakao.Share.sendDefault({
      objectType: 'feed',
      content: {
        title: '푸디무디',
        description: `'진짜'만 모은 맛잘알들의 SNS!`,
        imageUrl: imageUrl,
        link: {
          mobileWebUrl: `${target}/detail/feed/${targetId}`,
          webUrl: `${target}/detail/feed/${targetId}`,
        },
      },
      buttons: [
        {
          title: '둘러보기',
          link: {
            mobileWebUrl: `${target}/detail/feed/${targetId}`,
            webUrl: `${target}/detail/feed/${targetId}`,
          },
        },
      ],
    });
  };

  return <div onClick={handleShare}>공유하기</div>;
};
