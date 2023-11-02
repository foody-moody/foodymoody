import Slider from 'react-slick';
import 'slick-carousel/slick/slick-theme.css';
import 'slick-carousel/slick/slick.css';
import { styled } from 'styled-components';
import { MenuRateTag } from '../menuRateTag/MenuRateTag';

type Props = {
  images: FeedImage[];
};

export const Carousel: React.FC<Props> = ({ images }) => {
  const settings = {
    className: 'slider-items',
    infinite: false,
    speed: 500,
    slideToShow: 1,
    slidesToScroll: 1,
    dots: true,
    arrows: true,
  };

  return (
    <>
      <CustomCarousel {...settings}>
        {images.map((image) => (
          <Slide key={image.id}>
            <Image src={image.imageUrl} /> {/* alt 추가하기. */}
            <MenuRateTag
              menu={{ name: image.menu.name, rating: image.menu.rating }}
            />
          </Slide>
        ))}
      </CustomCarousel>
    </>
  );
};

const CustomCarousel = styled(Slider)`
  margin-bottom: -8px; /* 수정 예정 */
  width: 100%;
  .slick-dots {
    width: 100%;
    bottom: 34px;
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
          transform: translate(-50%, -50%);
          opacity: 1;
          border-radius: ${({ theme: { radius } }) => radius.small};
          background-color: ${({ theme: { colors } }) => colors.white};
        }
      }
    }
  }

  .slick-arrow {
    border-radius: ${({ theme: { radius } }) => radius.half};
    border: 1px solid ${({ theme: { colors } }) => colors.black};
    background-color: orange;
    width: 40px;
    height: 40px;
    z-index: 1;

    &::before {
      color: ${({ theme: { colors } }) => colors.black};
      font: ${({ theme: { fonts } }) => fonts.displayB24};
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }

    &:hover,
    &:focus {
      transition: all 0.3s;
      opacity: 0.75;
    }

    &.slick-disabled {
      opacity: 0;
      display: none;
      cursor: default;
      transition: all 0.3s;
    }
  }

  .slick-next {
    right: 10px;
  }

  .slick-prev {
    left: 10px;
  }
`;

const Slide = styled.div`
  position: relative;
  padding-bottom: 100%;
`;

const Image = styled.img`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
`;
