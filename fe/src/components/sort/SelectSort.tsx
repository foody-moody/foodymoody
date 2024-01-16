import { useSort } from 'recoil/sortState/useSort';
import { styled } from 'styled-components';
import { ArrowDownIcon } from 'components/common/icon/icons';

export const SelectSort = () => {
  const { handleSort } = useSort('collection');

  const OPTIONS = [
    { id: '1', name: '최신순', value: 'createdAt' },
    { id: '2', name: '좋아요순', value: 'heartCount' },
  ];

  return (
    <SelectLabel>
      <Select onChange={handleSort}>
        {OPTIONS.map((option: { id: string; name: string; value: string }) => (
          <Option key={option.id} value={option.value}>
            {option.name}
          </Option>
        ))}
      </Select>
      <ArrowDownIcon />
    </SelectLabel>
  );
};
const SelectLabel = styled.label`
  position: relative;
  width: 90px;
  svg {
    position: absolute;
    right: 4px;
    top: 50%;
    transform: translateY(-50%);
  }
`;

const Select = styled.select`
  -webkit-appearance: none;
  padding: 8px;
  width: 90px;
  background: white;
  border: 1px solid ${({ theme: { colors } }) => colors.black};

  border-radius: 4px;
  background: ${({ theme: { colors } }) => colors.white};
  cursor: pointer;
  font: ${({ theme: { fonts } }) => fonts.displayM12};

  &:focus {
    outline: none;
    border-color: ${({ theme: { colors } }) => colors.textTertiary};
  }
`;

const Option = styled.option``;
