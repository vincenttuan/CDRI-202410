https://dev.to/abhi0498/react-micro-frontends-using-vite-30ah

host-app 與 todo-components 專案下都要加上
npm install @originjs/vite-plugin-federation --save-dev

例如:
...\my-react-micro\host-app
npm install @originjs/vite-plugin-federation --save-dev

...\my-react-micro\todo-components
npm install @originjs/vite-plugin-federation --save-dev

----------------------------------------------------------------
todo-components 專案下
    npm run build

todo-components (port:4173)
    npm run preview

----------------------------------------------------------------
host-app 專案下
    npm run build

host-app (port:5172)
    npm run dev