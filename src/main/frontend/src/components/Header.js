import {useLocation} from "react-router-dom";


const Header = ({pageInfo}) => {

    const location = useLocation();

    const currentPage = pageInfo.find(page => page.url === location.pathname);

    return(
        <>
            <header className={"header"}>
                <h1>{currentPage ? currentPage.name : "페이지 없음"}</h1>
            </header>
        </>
    )
}

export default Header;