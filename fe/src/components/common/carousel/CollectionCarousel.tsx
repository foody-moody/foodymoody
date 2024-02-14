import { useState } from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick-theme.css';
import 'slick-carousel/slick/slick.css';
import { styled } from 'styled-components';
import { GridItem } from 'components/collection/GridItem';

// type Props = {
//   collection: CarouselCollectionItem[];
// };

const MOCK_DATA: CarouselCollectionItem[] = [
  {
    id: '1',
    author: {
      id: 'fd9ecdc16496ef07ec38ccef',
      name: '아티',
      profileImageUrl:
        'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1',
    },
    title: '테스트 컬렉션',
    thumbnailUrl: 'https://news.kbs.co.kr/data/news/2017/01/04/3405677_bH6.jpg',
    likeCount: 0,
    feedCount: 5,
    liked: false,
  },
  {
    id: '2',
    author: {
      id: 'fd9ecdc16496ef07ec38ccef',
      name: '아티',
      profileImageUrl:
        'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1',
    },
    title: '테스트 컬렉션',
    thumbnailUrl: 'https://news.kbs.co.kr/data/news/2017/01/04/3405677_bH6.jpg',
    likeCount: 0,
    feedCount: 5,
    liked: false,
  },
  {
    id: '3',
    author: {
      id: 'fd9ecdc16496ef07ec38ccef',
      name: '아티',
      profileImageUrl:
        'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1',
    },
    title: '테스트 컬렉션',
    thumbnailUrl: 'https://news.kbs.co.kr/data/news/2017/01/04/3405677_bH6.jpg',
    likeCount: 0,
    feedCount: 5,
    liked: false,
  },
  {
    id: '1asfasd',
    author: {
      id: 'fd9ecdc16496ef07ec38ccef',
      name: '아티',
      profileImageUrl:
        'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1',
    },
    title: '테스트 컬렉션',
    thumbnailUrl: 'https://news.kbs.co.kr/data/news/2017/01/04/3405677_bH6.jpg',
    likeCount: 0,
    feedCount: 5,
    liked: false,
  },
  {
    id: '2cbzdf',
    author: {
      id: 'fd9ecdc16496ef07ec38ccef',
      name: '아티',
      profileImageUrl:
        'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1',
    },
    title: '테스트 컬렉션',
    thumbnailUrl:
      'https://cdn.discordapp.com/attachments/1165365316428243147/1196350461616332901/e3398b6d7b81675eb4e8454b3c1540d3.png?ex=65b74f31&is=65a4da31&hm=b3538186963d6668f0ac30c8aa7430ef7fb160bb251b59f1364da25ba03f81ce&',
    likeCount: 0,
    feedCount: 5,
    liked: false,
  },
  {
    id: '2asdfgasdfg',
    author: {
      id: 'fd9ecdc16496ef07ec38ccef',
      name: '아티',
      profileImageUrl:
        'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1',
    },
    title: '테스트 컬렉션',
    thumbnailUrl:
      'https://cdn.discordapp.com/attachments/1165365316428243147/1196350461616332901/e3398b6d7b81675eb4e8454b3c1540d3.png?ex=65b74f31&is=65a4da31&hm=b3538186963d6668f0ac30c8aa7430ef7fb160bb251b59f1364da25ba03f81ce&',
    likeCount: 0,
    feedCount: 5,
    liked: false,
  },
  {
    id: '21214',
    author: {
      id: 'fd9ecdc16496ef07ec38ccef',
      name: '아티',
      profileImageUrl:
        'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1',
    },
    title: '테스트 컬렉션',
    thumbnailUrl:
      'https://cdn.discordapp.com/attachments/1165365316428243147/1196350461616332901/e3398b6d7b81675eb4e8454b3c1540d3.png?ex=65b74f31&is=65a4da31&hm=b3538186963d6668f0ac30c8aa7430ef7fb160bb251b59f1364da25ba03f81ce&',
    likeCount: 0,
    feedCount: 5,
    liked: false,
  },
];

export const CollectionCarousel: React.FC = () => {
  const [isDragging, setIsDragging] = useState(false);
  const handleBeforeChange = () => {
    setIsDragging(true);
  };

  const handleAfterChange = () => {
    setIsDragging(false);
  };

  const settings = {
    className: 'slider-items',
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3,
    initialSlide: 0,
    arrows: true,
    autoplay: true,
    autoplaySpeed: 5000,
    touchThreshold: 100,
    beforeChange: handleBeforeChange,
    afterChange: handleAfterChange,

    responsive: [
      {
        breakpoint: 500,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
          initialSlide: 2,
        },
      },
    ],
  };

  return (
    <>
      <CustomCarousel {...settings}>
        {MOCK_DATA.map((collection) => (
          <Slide key={collection.id}>
            <GridItem collection={collection} isDragging={isDragging} />
          </Slide>
        ))}
      </CustomCarousel>
    </>
  );
};

const CustomCarousel = styled(Slider)`
  width: 100%;

  .slick-dots {
    width: 100%;
    padding: 0px 12px;
    border-radius: 10.5px;
    display: flex !important;
    justify-content: center;
    align-items: center;
    gap: 8px;
    li {
      display: block;
      width: fit-content;
      position: relative;
      &.slick-active {
        button::before {
          background-color: ${({ theme: { colors } }) => colors.pink};
          width: 16px;
        }
      }
      button {
        position: relative;

        width: fit-content;
        height: 100%;
        padding: 0;
        &::before {
          content: '';
          display: block;
          width: 8px;
          height: 8px;
          position: absolute;
          left: 50%;
          top: 50%;
          border: 1px solid ${({ theme: { colors } }) => colors.black};
          transform: translate(-50%, -50%);
          opacity: 1;
          border-radius: ${({ theme: { radius } }) => radius.small};
          background-color: ${({ theme: { colors } }) => colors.white};
        }
      }
    }
  }
`;

const Slide = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
`;
