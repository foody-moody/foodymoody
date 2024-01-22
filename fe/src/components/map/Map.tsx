import { useEffect, useRef, useState } from 'react';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';

type Props = {
  data?: StoreDetail;
  name?: string;
  x?: number;
  y?: number;
};
const { naver } = window;

export const NaverMap: React.FC<Props> = ({ data, name, x, y }) => {
  const [coord, setCoord] = useState({
    x: '',
    y: '',
  });
  const mapRef = useRef(null);

  useEffect(() => {
    if (!naver) return;
    if (!data) return;
    console.log('naver geo service called');
    console.log(data?.roadAddress, 'roadAddress');

    naver.maps.Service.geocode(
      { query: data?.roadAddress },
      function (status, response) {
        if (status !== naver.maps.Service.Status.OK) {
          return alert('Something wrong!');
        }

        const result = response.v2, // 검색 결과의 컨테이너
          items = result.addresses; // 검색 결과의 배열

        setCoord({
          x: items[0].x,
          y: items[0].y,
        });
      }
    );
  }, [data]);

  useEffect(() => {
    if (!mapRef.current || !naver) return;

    const location = new naver.maps.LatLng(coord.y, coord.x);
    console.log(location, 'location');

    //데이터 요청으로 받은 좌표 넣어주기
    const mapOptions = {
      center: location,
      zoom: 18,
      // zoomControl: true,
    };

    const map = new naver.maps.Map(mapRef.current, mapOptions);
    const marker = new naver.maps.Marker({
      position: location,
      map,
    });
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    naver.maps.Event.addListener(marker, 'click', function (e: any) {
      const overlay = e.overlay, // marker
        position = overlay.getPosition(),
        url =
          'http://map.naver.com/index.nhn?enc=utf8&lng=' +
          position.lng() +
          '&lat=' +
          position.lat() +
          `&pinTitle=${name}&pinType=SITE`;
      // pintitle에 데이터 요청으로 받은 가게이름 넣기
      window.open(url);
    });
  }, [coord]);

  //   naver.maps.Event.addListener(map, 'click', function (e: any) {
  //     const // overlay = e.overlay, // marker
  //       // position = overlay.getPosition(),
  //       url =
  //         'http://map.naver.com/index.nhn?enc=utf8&lng=' +
  //         126.9769 +
  //         '&lat=' +
  //         37.5656 +
  //         `&pinTitle=${name}&pinType=SITE`;
  //     // pintitle에 데이터 요청으로 받은 가게이름 넣기
  //     window.open(url);
  //   });
  // }, []);

  return <Wrapper ref={mapRef}></Wrapper>;
};

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  border: 1px solid ${({ theme: { colors } }) => colors.red};

  ${media.sm} {
    width: 100%;
    height: 100%;
  }
`;
