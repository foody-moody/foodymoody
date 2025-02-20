import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { RouteContainer } from 'RouteContainer';
import { HelmetProvider } from 'react-helmet-async';
// import { RouterProvider } from 'react-router-dom';
import { RecoilRoot, RecoilEnv } from 'recoil';
import { ThemeProvider } from 'styled-components';
// import router from 'routes/router';
import { theme } from 'styles/designSystem';
import { GlobalStyles } from 'styles/globalStyles.ts';
import { GlobalModals } from 'components/common/modal/Modal';
import { GlobalToasts } from 'components/common/toast/Toast';

export const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 0,
    },
  },
});

const helmetContext = {};

function App() {
  RecoilEnv.RECOIL_DUPLICATE_ATOM_KEY_CHECKING_ENABLED = false;

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <ReactQueryDevtools initialIsOpen={false} />
        <ThemeProvider theme={theme}>
          <RecoilRoot>
            <GlobalStyles />
            <GlobalModals />
            <GlobalToasts />
            {/* <RouterProvider router={router} /> */}
            <HelmetProvider context={helmetContext}>
              <RouteContainer />
            </HelmetProvider>
          </RecoilRoot>
        </ThemeProvider>
      </QueryClientProvider>
    </>
  );
}

export default App;
