const customMediaQuery = (maxWidth: number) =>
  `@media (max-width: ${maxWidth}px)`;

export const media = {
  xs: customMediaQuery(500),
  sm: customMediaQuery(768),
  md: customMediaQuery(988),
  lg: customMediaQuery(1280),
};
