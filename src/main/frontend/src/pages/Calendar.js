import FullCalendar from "@fullcalendar/react";
import multiMonthPlugin from "@fullcalendar/multimonth";
import interactionPlugin from "@fullcalendar/interaction"
import {useNavigate} from "react-router-dom";
import axios from "axios";

const Calendar = () => {

    const navigate = useNavigate();

    const linkToMonthHandler = (info) => {
        const month = info.getMonth() + 1; // 특정 월
        alert(month);
        navigate("/calendar/" + month);
    }


    return (
        <>
            <FullCalendar
                plugins={[multiMonthPlugin, interactionPlugin]}
                initialView="multiMonthYear"
                locale="kr"
                headerToolbar={{
                    center:"title",
                    start:"prev",
                    end:"next"
                }}
                footerToolbar={{
                    center:"today"
                }}
                navLinks={true}
                navLinkDayClick={linkToMonthHandler}
            />
        </>
    )
}
export default Calendar;