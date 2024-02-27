import {useLocation} from "react-router-dom";


const Header = ({pageInfo}) => {

    const location = useLocation();

    const currentPage = pageInfo.find(page => page.url === location.pathname);

    // 캘린더 세부 페이지 헤더 설정
    const calendarDetail = location.pathname;
    const isCalendar = () => {
        if(calendarDetail.includes("calendar")){
            return "캘린더";
        }else {
            return "페이지 없음";
        }
    }

    return(
        <>
            <header className={"header"}>
                <h1>{currentPage ? currentPage.name : isCalendar()}</h1>
            </header>
        </>
    )
}

export default Header;