import react from '@vitejs/plugin-react-swc';
import { visualizer } from 'rollup-plugin-visualizer';
import { defineConfig, type PluginOption } from 'vite';
import svgr from 'vite-plugin-svgr';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
  plugins: [
    react(),
    svgr(),
    tsconfigPaths(),
    visualizer({
      filename: './dist/report.html',
      open: true,
      brotliSize: true,
    }) as PluginOption,
  ],
  optimizeDeps: {
    include: ['emoji-picker-react'],
  },
});
