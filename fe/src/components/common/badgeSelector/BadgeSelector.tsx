import { styled } from 'styled-components';
import { Badge } from '../badge/Badge';

type Props = {
  variant: BadgeVariantType;
  maxCount: number;
  selectedBadgeList: BadgeListType;
  onActiveBadge: (badges: BadgeListType) => void;
};

export const BadgeSelector: React.FC<Props> = ({
  variant,
  maxCount,
  selectedBadgeList,
  onActiveBadge,
}) => {
  /* TODO. variant별 BadgeList fetch 필요 */
  const MOCK_BADGELIST = [
    { id: '1', name: '뱃지1' },
    { id: '2', name: '뱃지2' },
    { id: '3', name: '뱃지3' },
    { id: '4', name: '뱃지4' },
    { id: '5', name: '뱃지5' },
    { id: '6', name: '뱃지6' },
    { id: '7', name: '뱃지7' },
    { id: '8', name: '뱃지8' },
    { id: '9', name: '뱃지9' },
  ];

  const isActiveBadge = (badgeId: string) =>
    selectedBadgeList.some((badge) => badge.id === badgeId);

  const isBadgeSelectable = (badgeId: string) => {
    return selectedBadgeList.length < maxCount || isActiveBadge(badgeId);
  };

  const removeBadgeFromList = (badgeId: string) => {
    return selectedBadgeList.filter((badge) => badge.id !== badgeId);
  };

  const getOnClickHandler = (badge: BadgeType) => {
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
      {MOCK_BADGELIST.map((badge) => (
        <Badge
          variant={variant}
          key={badge.id}
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
