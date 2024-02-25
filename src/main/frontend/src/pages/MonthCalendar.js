import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid"
import {useNavigate, useParams} from "react-router-dom";
import {useEffect} from "react";


const MonthCalendar = () => {

    const {year, month} = useParams();
    const nagivate = useNavigate();

    const thisMonth = () => {
        if(month < 10){
            const calendar = year + "-0" + month + "-" + "01";
            console.log(calendar)
            return calendar;
        }else{
            const calendar = year + "-" + month + "-" + "01";
            console.log(calendar)
            return calendar;
        }
    }

    useEffect(() => {
        const titleElement = document.querySelector(".fc-toolbar-title");

        const goBack = () => {
            nagivate(-1);
        }

        if(titleElement){
            titleElement.addEventListener("click", goBack);
        }

        return () => {
            if(titleElement){
                titleElement.removeEventListener("click", goBack);
            }
        }
    }, [year, month, nagivate]);

    return (
        <>
            <FullCalendar
            plugins={[dayGridPlugin]}
            initialView="dayGridMonth"
            initialDate={thisMonth()}
            headerToolbar={{
                center:"title",
                start:"prev",
                end:"next"
            }}
            locale="kr"
            titleFormat={{ month: 'long' }}
            />
        </>
    )
}

export default MonthCalendar;