import {Outlet} from "react-router-dom";
import Header from "../components/Header";
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";
import "../css/Layout.css"

const Layout = () => {

    return (
        <>
            <Header/>
            <NavBar/>
            <Outlet/>
            <Footer/>
        </>
    )
}

export default Layout;