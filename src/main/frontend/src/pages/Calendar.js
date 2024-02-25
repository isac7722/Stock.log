import FullCalendar from "@fullcalendar/react";
import multiMonthPlugin from "@fullcalendar/multimonth";
import interactionPlugin from "@fullcalendar/interaction"
import {useNavigate} from "react-router-dom";
const Calendar = () => {

    const navigate = useNavigate();

    const linkToMonthHandler = (info) => {
        const year = info.getFullYear();
        const month = info.getMonth() + 1;
        navigate(`/calendar/${year}/${month}`);
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
                navLinks={true}
                navLinkDayClick={linkToMonthHandler}
            />
        </>
    )
}
export default Calendar;