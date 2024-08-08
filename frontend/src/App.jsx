import { RouterProvider, createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import { HashRouter, Routes } from 'react-router-dom';

import Home from "./pages/Home";

// NOTE: change 'false' into 'true' in order to use HashRouter for single page build / application and vice versa
const single_build = false;

const page_router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<Home />} />
    </>
  )
);

function RouterForSingleFile() {
  return (
    <HashRouter>
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </HashRouter>
  );
}

function App() {
  return (
    <>
      {/* NOTE: RouterForSingleFile is intended for single page application (by using vite-single-file) */}
      { single_build === false ? <RouterProvider router={page_router} /> : <RouterForSingleFile /> }
    </>
  );
}

export default App;
