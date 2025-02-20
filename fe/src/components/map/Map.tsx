import { useLayoutEffect, useRef, useState } from 'react';
import { useToast } from 'recoil/toast/useToast';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';

type Props = {
  data?: StoreDetail;
};

const { naver } = window;

export const NaverMap: React.FC<Props> = ({ data }) => {
  const toast = useToast();
  const [coord, setCoord] = useState({
    x: '',
    y: '',
  });
  const [isError, setIsError] = useState(false);

  const mapRef = useRef(null);

  console.log('data in map', data);

  useLayoutEffect(() => {
    // 좌표계 EPSG:5174(EPSG:2097) => WGS84 변환
    if (!naver || !data) return;

    naver.maps.Service.geocode(
      { query: data?.roadAddress },
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      function (status: any, response: any) {
        const result = response.v2,
          items = result.addresses;

        if (status !== naver.maps.Service.Status.OK || items.length === 0) {
          setIsError(true);
          return toast.error('주소를 찾을 수 없습니다');
        }

        setCoord({
          x: items[0].x,
          y: items[0].y,
        });
      }
    );
  }, [data?.roadAddress]);

  useLayoutEffect(() => {
    // 좌표 변환 후 맵 렌더
    if (!mapRef.current || !naver || coord.x === '') {
      return;
    }

    const location = new naver.maps.LatLng(coord.y, coord.x);

    const mapOptions = {
      center: location,
      zoom: 18,
    };

    const map = new naver.maps.Map(mapRef.current, mapOptions);
    const marker = new naver.maps.Marker({
      position: location,
      map,
    });

    const handleClick = () => {
      const url =
        'http://map.naver.com/index.nhn?enc=utf8&lng=' +
        coord.x +
        '&lat=' +
        coord.y +
        `&pinTitle=${data?.name}&pinType=SITE`;
      window.open(url);
    };

    naver.maps.Event.addListener(map, 'click', handleClick);
    naver.maps.Event.addListener(marker, 'click', handleClick);
  }, [coord]);

  return (
    <>
      {isError ? (
        <ErrorHelper>지도를 불러올 수 없습니다.</ErrorHelper>
      ) : (
        <Wrapper ref={mapRef} />
      )}
    </>
  );
};

const Wrapper = styled.div`
  object-fit: cover;
  width: 100%;
  height: 400px;

  ${media.md} {
    height: 400px;
  }

  ${media.xs} {
    height: 320px;
  }
`;

const ErrorHelper = styled.div`
  width: 100%;
  height: 400px;

  display: flex;
  justify-content: center;
  align-items: center;
  background: ${({ theme: { colors } }) => colors.bgGray50};

  ${media.md} {
    height: 400px;
  }

  ${media.xs} {
    height: 320px;
  }
`;
