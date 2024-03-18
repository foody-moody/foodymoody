import { Helmet } from 'react-helmet-async';

export type Props = {
  children?: React.ReactNode;
  title?: string | null;
  meta?: any; // TODO: 타입 정의
};

export const CustomHelmet = (props: Props) => {
  console.log('CustomHelmet', props);

  return (
    <Helmet>
      {props.title && <title>푸디무디 - {props.title}</title>}
      <meta name="keywords" content={props.meta?.keywords} />
      <meta name="description" content={props.meta?.description} />
      <meta property="og:title" content={`푸디무디 - ${props.meta?.ogTitle}`} />
      <meta property="og:description" content={props.meta?.ogDescription} />
      <meta property="og:image" content={props.meta?.ogImage} />
      <meta property="og:image:width" content="1200" />
      <meta property="og:image:height" content="630" />
      <meta property="og:url" content={props.meta?.ogUrl} />
      <meta property="og:type" content={props.meta?.ogType} />
      <meta property="og:site_name" content={props.meta?.ogSiteName} />
      <meta property="og:locale" content={props.meta?.ogLocale} />

      <meta name="twitter:title" content={props.meta?.twitterTitle} />
      <meta
        name="twitter:description"
        content={props.meta?.twitterDescription}
      />
      <meta name="twitter:image" content={props.meta?.twitterImage} />
      <meta name="twitter:url" content={props.meta?.twitterUrl} />

      {props.meta?.canonical && (
        <link rel="canonical" href={props.meta?.canonical} />
      )}
    </Helmet>
  );
};
