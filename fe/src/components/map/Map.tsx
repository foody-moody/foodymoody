import { useEffect, useRef, useState } from 'react';
import { useToast } from 'recoil/toast/useToast';
import { styled } from 'styled-components';

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

  const mapRef = useRef(null);

  useEffect(() => {
    // 좌표계 EPSG:5174(EPSG:2097) => WGS84 변환
    if (!naver || !data) return;

    naver.maps.Service.geocode(
      { query: data?.roadAddress },
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      function (status: any, response: any) {
        if (status !== naver.maps.Service.Status.OK) {
          // error throw
          return toast.error('주소를 찾을 수 없습니다');
        }

        const result = response.v2,
          items = result.addresses;

        setCoord({
          x: items[0].x,
          y: items[0].y,
        });
      }
    );
  }, [data?.roadAddress]);

  useEffect(() => {
    // 좌표 변환 후 맵 렌더
    if (!mapRef.current || !naver) return;

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

  return <Wrapper ref={mapRef} />;
};

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  object-fit: cover;
`;
