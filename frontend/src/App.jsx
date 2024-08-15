import { RouterProvider, createBrowserRouter, createRoutesFromElements, Route, useParams } from "react-router-dom";
import { HashRouter, Routes } from 'react-router-dom';

import Home from "./pages/Home";
import Result from "./pages/Result";
import AddWebPage from "./pages/AddWebPage";

// NOTE: change 'false' into 'true' in order to use HashRouter for single page build / application and vice versa
const single_build = false;

function ResultPage() {
  let {query} = useParams();

  return (
    <Result search_query={query} />
  );
}

const page_router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<Home />} />
      <Route path="result/:query" element={<ResultPage />} />
      <Route path="add_web_page" element={<AddWebPage />} />
    </>
  )
);

function RouterForSingleFile() {
  return (
    <HashRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="result/:query" element={<ResultPage />} />
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
