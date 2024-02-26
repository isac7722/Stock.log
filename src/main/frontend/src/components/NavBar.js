import {Link} from "react-router-dom";

const NavBar = () => {

    return(
        <nav className={"navbar"}>
            <imageWrapper id={"logoBox"} >
                <img id={"logo"} src={"/img/logo.png"} alt={"로고 이미지"}/>
            </imageWrapper>
            <div className={"navbarList"}>
                <h6 id={"navbarTitle"}>MAIN MENU</h6>
                <Link className={"navbarMenu"} to={"/main"}>메인화면</Link>
                <Link className={"navbarMenu"} to={"/journals"}>매매일지</Link>
                <Link className={"navbarMenu"} to={"/calendar"}>캘린더</Link>
                <Link className={"navbarMenu"} to={"/trader"}>트레이더스</Link>
                <Link className={"navbarMenu"} to={"/settings"}>설정</Link>
                <button className={"navbarMenu"} type={"button"}>Logout</button>
            </div>

        </nav>
    )
}

export default NavBar;