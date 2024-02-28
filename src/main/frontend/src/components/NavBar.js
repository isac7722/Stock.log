import {Link} from "react-router-dom";
import "../css/Layout.css"
import Button from 'react-bootstrap/Button';


const NavBar = ({pageInfo}) => {

    const pageInfoList = () => pageInfo.map(page => {
        return(
        <div className="navDiv">
            <img src={page.imgUrl} className="navLogo" />
            <Link className={"navbarMenu"} to={page.url}>{page.name}</Link>
        </div>
        )
    })

    return(
        <>
            <nav id={"navbar"}>
                <div id={"logoBox"}>
                    <img id={"logo"} src={"/img/logo.png"} alt={"로고 이미지"}/>
                </div>
                <div className={"navbarList"}>
                    <Button size="lg" id="journalsAddBtn">
                        매매일지 추가
                    </Button>
                    <h6 id={"navbarTitle"}>MAIN MENU</h6>
                    {pageInfoList()}
                    {/* <button className={"navbarMenu"} type={"button"}>Logout</button> */}
                </div>

            </nav>
        </>
    )
}

export default NavBar;