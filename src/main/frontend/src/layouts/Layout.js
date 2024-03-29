import {Outlet} from "react-router-dom";
import Header from "../components/Header";
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";
import "../css/Layout.css"
import pageInfomation from "../layouts/layout.json"
const Layout = () => {

    return (
        <>
            <Header pageInfo={pageInfomation}/>
            <NavBar pageInfo={pageInfomation}/>
            <div id={"mainContents"}>
                <Outlet/>
            </div>
            <Footer/>
        </>
    )
}

export default Layout;