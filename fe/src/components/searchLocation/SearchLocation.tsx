import { useState } from 'react';
import { useToggle } from 'recoil/booleanState/useToggle';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import { TextButton } from 'components/common/button/TextButton';
import {
  CloseSmallIcon,
  MapPinSmallIcon,
  SearchIcon,
} from 'components/common/icon/icons';
import { SearchPanelInput } from 'components/searchPanelInput/SearchPanelInput';

type Props = {
  locationName: string;
  locationNameHelperText?: string;
  handleLocationChange: (value: string) => void;
};

export const SearchLocation: React.FC<Props> = ({
  locationName,
  locationNameHelperText,
  handleLocationChange,
}) => {
  const [searchResult, setSearchResult] = useState([]);
  const search = useToggle('search');
  const tool = useToggle('tool');

  const [selectedLocation, setSelectedLocation] = useState({
    id: '',
    placeName: '',
    addressName: '',
    roadAddressName: '',
    x: '',
    y: '',
    url: '',
    phone: '',
    distance: '',
    category_name: '',
    category_group_code: '',
  });

  const handleOpenTools = () => {
    !search.isTrue && tool.toggle();
  };

  const handleCloseTools = () => {
    tool.toggleOff();
  };

  const handleOpenSearch = () => {
    search.toggleOn();
  };

  const handleCloseSearch = () => {
    search.toggleOff();
  };

  const handleSelectLocation = (location: any) => {
    console.log(location, 'location');

    setSelectedLocation({
      id: location.id,
      placeName: location.place_name,
      addressName: location.address_name,
      roadAddressName: location.road_address_name,
      x: location.x,
      y: location.y,
      url: location.place_url,
      phone: location.phone,
      distance: location.distance,
      category_name: location.category_name,
      category_group_code: location.category_group_code,
    });
  };

  const handleSearchLocation = (locationName: string) => {
    handleLocationChange(locationName);
    handleSearch(locationName);
  };

  ///

  const handleSearch = (keyword: string) => {
    console.log(keyword, 'keyword');

    window.kakao.maps.load(() => {
      const ps = new window.kakao.maps.services.Places();
      ps.keywordSearch(keyword, placesSearchCB);
    });
  };

  const placesSearchCB = (data, status) => {
    if (status === window.kakao.maps.services.Status.OK) {
      console.log(data, 'kakao search data');
      setSearchResult(data);
    } else if (status === window.kakao.maps.services.Status.ZERO_RESULT) {
      console.log('검색 결과가 존재하지 않습니다.');
      return;
    } else if (status === window.kakao.maps.services.Status.ERROR) {
      alert('검색 결과 중 오류가 발생했습니다.');
      return;
    }
  };

  return (
    <Wrapper>
      <Location>
        {selectedLocation.placeName && (
          <SelectedLocation onClick={handleOpenTools}>
            <MapPinSmallIcon />
            {selectedLocation.placeName}
          </SelectedLocation>
        )}
        {tool.isTrue && (
          <Button
            size="xs"
            backgroundColor="orange"
            width={40}
            onClick={() => {
              handleOpenSearch();
              handleCloseTools();
            }}
          >
            <SearchIcon />
          </Button>
        )}
      </Location>

      {search.isTrue && (
        <SearchContainer>
          <SearchPanelInput
            variant="underline"
            value={locationName}
            // onChangeValue={handleLocationChange}
            onChangeValue={handleSearchLocation}
            helperText={locationNameHelperText}
            data={searchResult}
            onSelectLocation={handleSelectLocation}
          />
          {selectedLocation.placeName && (
            <TextButton size="m" color="black" onClick={handleCloseSearch}>
              <CloseSmallIcon />
            </TextButton>
          )}
        </SearchContainer>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 8px;
`;

const Location = styled.div`
  width: 100%;
  display: flex;
  gap: 8px;
  align-items: center;
`;

const SelectedLocation = styled.div`
  width: fit-content;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;

  padding: 8px 16px;
  cursor: pointer;
  font: ${({ theme: { fonts } }) => fonts.displayB12};
  color: ${({ theme: { colors } }) => colors.textSecondary};
  background: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: 20px;
`;

const SearchContainer = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  gap: 8px;
`;
