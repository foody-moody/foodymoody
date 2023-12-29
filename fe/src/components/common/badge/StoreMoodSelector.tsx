import { useGetStoreMood } from 'service/queries/mood';
import { styled } from 'styled-components';
import { StoreMoodBadge } from './StoreMoodBadge';

type Props = {
  selectedBadges?: Badge[];
  onSelectedBadges: (badges: Badge[]) => void;
};

export const StoreMoodSelector: React.FC<Props> = ({
  selectedBadges = [],
  onSelectedBadges,
}) => {
  const { data: badges } = useGetStoreMood();

  const handleSelectBadge = (selectedBadge: Badge) => {
    if (selectedBadges.some((badge) => badge.id === selectedBadge.id)) {
      onSelectedBadges(
        selectedBadges.filter((badge) => badge.id !== selectedBadge.id)
      );
    } else {
      if (selectedBadges.length < 3) {
        onSelectedBadges([...selectedBadges, selectedBadge]);
      }
    }
  };

  return (
    <Wrapper>
      {badges?.map((badge: Badge) => (
        <StoreMoodBadge
          key={badge.id}
          name={badge.name}
          selected={selectedBadges.some((b) => b.id === badge.id)}
          onSelect={() => handleSelectBadge(badge)}
        />
      ))}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  gap: 8px;
`;
