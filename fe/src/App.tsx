import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { RouterProvider } from 'react-router-dom';
import { RecoilRoot, RecoilEnv } from 'recoil';
import { ThemeProvider } from 'styled-components';
import router from 'routes/router';
import { theme } from 'styles/designSystem';
import { GlobalStyles } from 'styles/globalStyles.ts';
import { GlobalModals } from 'components/common/modal/Modal';

// import { useRefreshToken } from 'hooks/auth/useAuth';

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
  // useRefreshToken();

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <ReactQueryDevtools initialIsOpen={false} />
        <ThemeProvider theme={theme}>
          <RecoilRoot>
            <GlobalStyles />
            <GlobalModals />
            <RouterProvider router={router} />
          </RecoilRoot>
        </ThemeProvider>
      </QueryClientProvider>
    </>
  );
}

export default App;
