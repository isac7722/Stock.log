import {Outlet} from "react-router-dom";
import Header from "../components/Header";
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";
import "../css/Layout.css"
import pageInfo from "../layouts/layout.json"
const Layout = () => {

    return (
        <>
            <Header/>
            <NavBar pageInfo={pageInfo}/>
            <Outlet/>
            <Footer/>
        </>
    )
}

export default Layout;