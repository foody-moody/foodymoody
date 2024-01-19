import { useToggle } from 'recoil/booleanState/useToggle';
import { styled } from 'styled-components';
import { Input } from 'components/common/input/Input';
import { InputField } from 'components/common/input/InputField';

type Props = {
  variant: 'ghost' | 'underline' | 'default' | 'comment' | 'rectangle';
  value?: string;
  data?: StoreItem[];
  onChangeValue?(value: string): void;
  onSelectLocation(location: SelectedStore): void;
};

export const SearchPanelInput: React.FC<Props> = ({
  variant,
  value,
  data,
  onChangeValue,
  onSelectLocation,
}) => {
  const search = useToggle('search');
  const handleSelect = (result: StoreItem) => {
    console.log(result, 'result');
    onSelectLocation(result);
    search.toggleOff();
  };

  return (
    <Wrapper>
      <Input variant={variant}>
        <Input.CenterContent>
          <InputField
            value={value}
            onChangeValue={onChangeValue}
            onClick={search.toggleOn}
          />
        </Input.CenterContent>
        <Input.BottomPanel isOpen={search.isTrue && value?.trim().length !== 0}>
          {data?.length === 0 ? (
            <ItemRow
              key={crypto.randomUUID()}
              onClick={() => {
                onChangeValue?.('');
              }}
            >
              <PlaceName>{'검색결과가 없어요'}</PlaceName>
            </ItemRow>
          ) : (
            <>
              {data?.map((result: StoreItem) => (
                <ItemRow
                  key={result.id}
                  onClick={() => {
                    handleSelect(result);
                  }}
                >
                  <PlaceName>{result.name}</PlaceName>
                  {result && (
                    <AddressText>
                      {result.roadAddress || result.address}
                    </AddressText>
                  )}
                </ItemRow>
              ))}
            </>
          )}
        </Input.BottomPanel>
      </Input>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
`;

const ItemRow = styled.li`
  width: 100%;

  display: flex;
  flex-direction: column;
  gap: 4px;
  padding: 8px;
  cursor: pointer;
  border-radius: 4px;
  &:hover {
    background-color: ${({ theme: { colors } }) => colors.bgGray50};
  }
`;

const PlaceName = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
`;

const AddressText = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
