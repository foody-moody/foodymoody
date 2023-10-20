import router from 'routes/router';
import { RouterProvider } from 'react-router-dom';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ThemeProvider } from 'styled-components';
import { theme } from 'styles/designSystem';
import { RecoilRoot } from 'recoil';
import { GlobalStyles } from 'styles/globalStyles.ts';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 0,
    },
  },
});

function App() {
  return (
    <>
      <QueryClientProvider client={queryClient}>
        <ReactQueryDevtools initialIsOpen={false} />
        <ThemeProvider theme={theme}>
          <RecoilRoot>
            <GlobalStyles />
            <RouterProvider router={router} />
          </RecoilRoot>
        </ThemeProvider>
      </QueryClientProvider>
    </>
  );
}

export default App;
