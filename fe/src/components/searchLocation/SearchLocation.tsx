import { useToggle } from 'recoil/booleanState/useToggle';
import { useGetStores } from 'service/queries/store';
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
  value: string;
  keyword: string;
  selectedStore: SelectedStore;
  onStoreChange: (value: string) => void;
  onSelectStore: (store: StoreItem) => void;
};

export const SearchLocation: React.FC<Props> = ({
  value,
  keyword,
  selectedStore,
  onStoreChange,
  onSelectStore,
}) => {
  const search = useToggle('search');
  const tool = useToggle('tool');
  const { data: stores } = useGetStores(keyword);

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

  const handleSelectLocation = (location: StoreItem) => {
    console.log(location, 'location');
    onSelectStore(location);
    onStoreChange(location.name);
  };

  const handleSearchLocation = (locationName: string) => {
    onStoreChange(locationName);
  };

  return (
    <Wrapper>
      <Location>
        {selectedStore.name && (
          <SelectedLocation onClick={handleOpenTools}>
            <MapPinSmallIcon />
            {selectedStore.name}
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
            value={value}
            data={stores}
            onChangeValue={handleSearchLocation}
            onSelectLocation={handleSelectLocation}
          />
          {selectedStore.name && (
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
