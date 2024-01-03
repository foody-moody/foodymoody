import { useState } from 'react';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { SearchIcon } from 'components/common/icon/icons';
import { Input } from 'components/common/input/Input';
import { InputField } from 'components/common/input/InputField';

type Props = {
  variant: 'ghost' | 'underline' | 'default' | 'comment' | 'rectangle';
  value?: string;
  helperText?: string;
  data: any;
  onChangeValue?(value: string): void;
  onSelectLocation(location: any): void;
  onCloseSearch(): void;
};

export const SearchPanelInput: React.FC<Props> = ({
  variant,
  value,
  helperText,
  data,
  onChangeValue,
  onSelectLocation,
  onCloseSearch,
}) => {
  // TODO panel 데이터 페치

  const handlePanelClose = () => {
    onChangeValue?.('');
  };

  return (
    <Wrapper>
      <Input variant={variant} helperText={helperText}>
        <Input.CenterContent>
          <InputField value={value} onChangeValue={onChangeValue} />
        </Input.CenterContent>
        {/* <Input.RightContent>
          <TextButton size="m" color="black" onClick={handleSearch}>
            <SearchIcon />
          </TextButton>
        </Input.RightContent> */}
        <Input.BottomPanel isOpen={value?.trim().length !== 0}>
          {data.map((result) => (
            <ItemRow
              key={result.id}
              onClick={() => {
                console.log(result, 'result');
                onSelectLocation(result);
                handlePanelClose();
                onCloseSearch();
              }}
            >
              <PlaceName>{result.place_name}</PlaceName>
              {result.road_address_name && (
                <AddressText>{result.road_address_name}</AddressText>
              )}
            </ItemRow>
          ))}
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
