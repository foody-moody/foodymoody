import 'styled-components';
import { ColorsTypes, FontsTypes, RadiusTypes } from 'styles/designSystem';

declare module 'styled-components' {
  export interface DefaultTheme {
    fonts: FontsTypes;
    colors: ColorsTypes;
    radius: RadiusTypes;
  }
}
