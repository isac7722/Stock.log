import {Outlet} from "react-router-dom";
import Header from "../components/Header";
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";
import "../css/Layout.css"
import pageInfo from "../layouts/layout.json"
const Layout = () => {

    return (
        <>
            <Header pageInfo={pageInfo}/>
            <NavBar pageInfo={pageInfo}/>
            <div id={"mainContents"}>
                <Outlet/>
            </div>
            <Footer/>
        </>
    )
}

export default Layout;