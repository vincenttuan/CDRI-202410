// vite.config.js in host-app
import { defineConfig } from "vite"; // Vite 提供的工具函式，用來定義並導出配置檔案，提供型別提示與更清晰的結構。
import react from "@vitejs/plugin-react"; // 這是一個專為 Vite 設計的 React 插件，負責處理 React 的特性（如 JSX 語法）。
import federation from "@originjs/vite-plugin-federation"; // 實現模組聯邦（Module Federation）的插件，用於微前端應用程式之間的模組共享。

export default defineConfig({
  plugins: [
    react(),
    federation({
      name: "host-app", // 定義當前應用程式的名稱，這裡是 host-app，代表它是主應用程式。
      remotes: { // 設定遠端模組的映射關係。
        todo_components: "http://localhost:4173/assets/remoteEntry.js", // 遠端應用程式的入口檔案位置，通常是通過 @originjs/vite-plugin-federation 自動生成。
      },
      shared: ["react"], // 列出需要共享的模組，這裡是 react，表示主應用與遠端應用共用 react，以減少重複加載。
    }),
  ],

  build: {
    modulePreload: false, // 禁用模組預加載，減少瀏覽器在預加載不必要資源時的開銷。
    target: "esnext", // 設定編譯目標為最新的 ES 規範，這樣可以利用現代瀏覽器的最佳性能。
    minify: false, // 禁用代碼壓縮，方便開發時進行調試。
    cssCodeSplit: false, // 禁用 CSS 分片，將所有 CSS 打包到一個檔案中，簡化資源管理。
  },
  server: {
    port: 5172, // 開發環境的端口，避免與其他專案衝突。透過 npm run dev 啟動
  },
  preview: {
    port: 4173, // 預覽環境的端口，透過 npm run preview 啟動
  },
});