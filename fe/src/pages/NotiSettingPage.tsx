import { useState } from 'react';
import { styled } from 'styled-components';
import { Switch } from 'components/common/switch/Switch';

// 임시 데이터 지울꺼임
const SETTING_EX = {
  allNotification: false, // 전체 댓글
  feedComment: false, // 파드 댓글
  collectionComment: true, // 콜렉션 댓글
  feedLike: true, // 피드 좋아요
  collectionLike: false, // 컬랙션 좋아요
  commentLike: false, // 댓글 좋아요
  follow: true, // 팔로우 알림
};

type NotiSettingType = {
  allNotification: boolean;
  feedComment: boolean;
  collectionComment: boolean;
  feedLike: boolean;
  collectionLike: boolean;
  commentLike: boolean;
  follow: boolean;
  // 나중에 멘션 알림 타입 추가해야함
};

/* TODO. API 수정되면 연동하기 */

export const NotiSettingPage = () => {
  const [settings, setSettings] = useState(SETTING_EX);

  // 나중에 수정할겡요
  const handleToggle = (setting: keyof NotiSettingType) => {
    const updatedSettings = { ...settings, [setting]: !settings[setting] };
    setSettings(updatedSettings);

    // 알림 변경 관련 요청
  };

  return (
    <Wrapper>
      <Section>
        <Title>알림</Title>
        <Switch
          isOn={settings.allNotification}
          onClick={() => handleToggle('allNotification')}
          label="전체 알림 해제"
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
