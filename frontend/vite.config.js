import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
// eslint-disable-next-line no-unused-vars
import { viteSingleFile } from 'vite-plugin-singlefile'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    // NOTE: Uncomment the line below in order to  build a single web page when running "npm run build" command
    // viteSingleFile(),
  ],
})
