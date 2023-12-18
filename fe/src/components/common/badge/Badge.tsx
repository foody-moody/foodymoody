import { useEffect, useState } from 'react';
import { styled } from 'styled-components';

type Props = {
  variant: MoodVariant;
  badge: Mood;
  selectedBadgeList?: Mood[];
  onClick?: (id: string, name: string) => void;
};

export const Badge: React.FC<Props> = ({
  variant,
  badge,
  selectedBadgeList,
  onClick,
}) => {
  const BadgeComponent = BADGE_VARIANT[variant];
  const [active, setActive] = useState(false);
  const isActive = onClick ? active : false;

  useEffect(() => {
    selectedBadgeList &&
      setActive(selectedBadgeList.some((selected) => selected.id === badge.id));
  }, [selectedBadgeList]);

  const handleClick = () => {
    if (onClick) {
      setActive(!active);
      onClick(badge.id, badge.name);
    }
  };

  return (
    <BadgeComponent $isActive={isActive} onClick={handleClick}>
      {badge.name}
    </BadgeComponent>
  );
};

const BaseBadge = styled.div<{ $isActive: boolean }>`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  width: fit-content;
  padding: 4px 12px;
  border: 1px solid;
  text-align: center;
`;

const TasteMoodBadge = styled(BaseBadge)`
  color: ${({ theme: { colors } }) => colors.yellow500};
  background-color: ${({ $isActive, theme: { colors } }) =>
    $isActive ? colors.yellow100 : colors.white};
  border-color: ${({ theme: { colors } }) => colors.yellow500};
  border-radius: ${({ theme: { radius } }) => radius.large};
  padding: 4px 12px;
`;

const StoreMoodBadge = styled(BaseBadge)`
  color: ${({ $isActive, theme: { colors } }) =>
    $isActive ? colors.blue100 : colors.blue500};
  background-color: ${({ $isActive, theme: { colors } }) =>
    $isActive ? colors.blue500 : colors.blue100};
  border-color: ${({ $isActive, theme: { colors } }) =>
    $isActive ? colors.blue500 : colors.blue100};
  border-radius: ${({ theme: { radius } }) => radius.small};
  padding: 3px 12px;
`;

const BADGE_VARIANT = {
  taste: TasteMoodBadge,
  store: StoreMoodBadge,
};
