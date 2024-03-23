import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@src': "/src",
      '@app': "/src/app",
      '@entities': "/src/entities",
      '@features': "/src/features",
      '@pages': "/src/pages",
      '@processes': "/src/processes",
      '@shared': "/src/shared",
      '@widgets': "/src/widgets",
      '@store': "/src/store",
      '@hook': "/src/hook",
    }
  },
})
