import { useGetStoreMood } from 'service/queries/mood';
import { styled } from 'styled-components';
import { Badge } from '../badge/Badge';

type Props = {
  variant: MoodVariant;
  maxCount: number;
  selectedBadgeList: Mood[];
  onActiveBadge: (badges: Mood[]) => void;
};

export const BadgeSelector: React.FC<Props> = ({
  variant,
  maxCount,
  selectedBadgeList,
  onActiveBadge,
}) => {
  /* TODO. variant별 BadgeList fetch 필요 */
  const { data: badges } = useGetStoreMood();
  console.log(selectedBadgeList, 'selectedBadgeList');
  console.log(badges, 'badges');

  const isActiveBadge = (badgeId: string) =>
    selectedBadgeList.some((badge) => badge.id === badgeId);

  const isBadgeSelectable = (badgeId: string) => {
    return selectedBadgeList.length < maxCount || isActiveBadge(badgeId);
  };

  const removeBadgeFromList = (badgeId: string) => {
    return selectedBadgeList.filter((badge) => badge.id !== badgeId);
  };

  const getOnClickHandler = (badge: Mood) => {
    if (!isBadgeSelectable(badge.id)) {
      return undefined;
    }
    return () => handleBadgeClick(badge.id, badge.name);
  };

  const handleBadgeClick = (id: string, name: string) => {
    if (isActiveBadge(id)) {
      onActiveBadge(removeBadgeFromList(id));
      return;
    }

    if (selectedBadgeList.length < maxCount) {
      onActiveBadge([...selectedBadgeList, { id, name }]);
    }
  };

  return (
    <Wrapper>
      {badges?.map((badge: Mood) => (
        <Badge
          variant={variant}
          key={badge.id}
          selectedBadgeList={selectedBadgeList}
          badge={badge}
          onClick={getOnClickHandler(badge)}
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
