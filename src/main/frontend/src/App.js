import {BrowserRouter, Route, Routes} from "react-router-dom";
import Layout from "./layouts/Layout";
import Main from "./pages/Main";
import Calendar from "./pages/Calendar";
import Journals from "./pages/Journals";
import Settings from "./pages/Settings";
import Admin from "./pages/Admin";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import FindPass from "./pages/FindPass";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path={"/login"} element={<Login/>}></Route>
          <Route path={"/signup"} element={<Signup/>}></Route>
          <Route path={"/findpass"} element={<FindPass/>}></Route>
          <Route path={"/"} element={<Layout/>}>
            <Route path={"main"} element={<Main/>}/>
            <Route path={"calendar"}>
              <Route index element={<Calendar/>}/>
            </Route>
            <Route path={"journals"} element={<Journals/>}/>
            <Route path={"settings"} element={<Settings/>}/>
          </Route>
          <Route path={"/admin"} element={<Admin/>}>

          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
