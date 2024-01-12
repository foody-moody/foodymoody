import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { RouteContainer } from 'RouteContainer';
// import { RouterProvider } from 'react-router-dom';
import { RecoilRoot, RecoilEnv } from 'recoil';
import { ThemeProvider } from 'styled-components';
// import router from 'routes/router';
import { theme } from 'styles/designSystem';
import { GlobalStyles } from 'styles/globalStyles.ts';
import { GlobalModals } from 'components/common/modal/Modal';
import { GlobalToasts } from 'components/common/toast/Toast';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 0,
    },
  },
});

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
            <RouteContainer />
          </RecoilRoot>
        </ThemeProvider>
      </QueryClientProvider>
    </>
  );
}

export default App;
