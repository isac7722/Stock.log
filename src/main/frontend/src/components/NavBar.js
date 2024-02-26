import {Link} from "react-router-dom";

const NavBar = ({pageInfo}) => {

    const pageInfoList = () => pageInfo.map(page => {
        return(
            <Link className={"navbarMenu"} to={page.url}>{page.name}</Link>
        )
    })

    return(
        <>
            <nav className={"navbar"}>
                <div id={"logoBox"}>
                    <img id={"logo"} src={"/img/logo.png"} alt={"로고 이미지"}/>
                </div>
                <div className={"navbarList"}>
                    <h6 id={"navbarTitle"}>MAIN MENU</h6>
                    {pageInfoList()}
                    <button className={"navbarMenu"} type={"button"}>Logout</button>
                </div>

            </nav>
        </>
    )
}

export default NavBar;