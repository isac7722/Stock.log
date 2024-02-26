import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid"
import {useNavigate, useParams} from "react-router-dom";
import {useEffect} from "react";


const MonthCalendar = () => {

    const {year, month} = useParams();
    const navigate = useNavigate();

    const thisMonth = () => {
        if(month < 10){
            return year + "-0" + month + "-" + "01";
        }else{
            return year + "-" + month + "-" + "01";
        }
    }

    useEffect(() => {
        const titleElement = document.querySelector(".fc-toolbar-title");

        const goBack = () => {
            navigate(-1);
        }

        if(titleElement){
            titleElement.addEventListener("click", goBack);
        }

        return () => {
            if(titleElement){
                titleElement.removeEventListener("click", goBack);
            }
        }
    }, [year, month, navigate]);

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
            />
        </>
    )
}

export default MonthCalendar;