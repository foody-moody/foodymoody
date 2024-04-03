import { useState, useEffect } from 'react';
import {
  useNotificationSettings,
  useUpdateAllNotificationSettings,
  useUpdateNotificationSettings,
} from 'service/queries/notification';
import { styled } from 'styled-components';
import { Switch } from 'components/common/switch/Switch';
import { debounce } from 'utils/debounce';

const DEFAULT_SETTING: NotiSettingType = {
  allNotification: false,
  feedComment: false,
  collectionComment: false,
  feedLike: false,
  collectionLike: false,
  commentLike: false,
  follow: false,
};

export const NotiSettingPage = () => {
  const { data: settingData } = useNotificationSettings();
  const [settings, setSettings] = useState(DEFAULT_SETTING);
  const { mutate: updateNotification } = useUpdateNotificationSettings();
  const { mutate: updateAllNotification } = useUpdateAllNotificationSettings();

  useEffect(() => {
    if (settingData) {
      setSettings(settingData);
    }
  }, [settingData]);

  const debouncedUpdateNotification = debounce(updateNotification, 200);
  const debouncedUpdateAll = debounce(updateAllNotification, 200);

  const handleToggle = (setting: keyof typeof DEFAULT_SETTING) => {
    if (setting === 'allNotification') {
      const newAllNotiState = !settings.allNotification;
      debouncedUpdateAll({ allow: newAllNotiState });
    } else {
      const updatedSettings = { ...settings, [setting]: !settings[setting] };
      debouncedUpdateNotification(updatedSettings);
    }
  };

  return (
    <Wrapper>
      <Section>
        <Title>알림</Title>
        <Switch
          isOn={settings.allNotification}
          onClick={() => handleToggle('allNotification')}
          label="전체 알림"
        />
      </Section>

      <Section>
        <Title>좋아요</Title>
        <Switch
          isOn={settings.feedLike}
          onClick={() => handleToggle('feedLike')}
          label="피드 좋아요"
        />
        <Switch
          isOn={settings.collectionLike}
          onClick={() => handleToggle('collectionLike')}
          label="컬렉션 좋아요"
        />
        <Switch
          isOn={settings.commentLike}
          onClick={() => handleToggle('commentLike')}
          label="댓글 및 대댓글 좋아요"
        />
      </Section>

      <Section>
        <Title>댓글</Title>
        <Switch
          isOn={settings.feedComment}
          onClick={() => handleToggle('feedComment')}
          label="피드 댓글"
        />
        <Switch
          isOn={settings.collectionComment}
          onClick={() => handleToggle('collectionComment')}
          label="컬렉션 댓글"
        />
      </Section>

      {/* <Title>멘션</Title> */}
      {/* 나중에 멘션 알림 생기면 추가 */}

      <Section>
        <Title>팔로우 요청</Title>
        <Switch
          isOn={settings.follow}
          onClick={() => handleToggle('follow')}
          label="팔로우 요청"
        />
      </Section>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  padding: 0 16px;
`;

const Title = styled.h2`
  font: ${({ theme: { fonts } }) => fonts.displayB20};
  margin-bottom: 8px;
`;

const Section = styled.div`
  margin-bottom: 32px;
`;
