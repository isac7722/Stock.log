import {Link} from "react-router-dom";

const NavBar = () => {

    return(
        <nav>
            <div>
                <img src={"/img/logo.png"} alt={"로고 이미지"}/>
            </div>
            <div>
                <h6>MAIN MENU</h6>
                <Link to={"/main"}>메인화면</Link>
                <Link to={"/journals"}>매매일지</Link>
                <Link to={"/calendar"}>캘린더</Link>
                <Link to={"/trader"}>트레이더스</Link>
                <Link to={"/settings"}>설정</Link>
                <button type={"button"}>Logout</button>
            </div>

        </nav>
    )
}

export default NavBar;