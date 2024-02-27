import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid"
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {toast, ToastContainer} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "../css/calendar/MonthCalendar.css"

const MonthCalendar = () => {

    const {year, month} = useParams();
    const navigate = useNavigate();
    const [isMouseOver, setIsMouseOver] = useState(false);

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

        const showNotification = (event) => {
            const mouseX = event.clientX;
            const mouseY = event.clientY;

            toast.info("올해 연도로 이동합니다.", {
                autoClose:false,
                position: "top-left",
                hideProgressBar:true,
                closeOnClick:false,
                closeButton:false,
                style:{top:mouseY + "px", left:mouseX + "px"}
            });

            setIsMouseOver(true);
        }

        const hideNotification = () => {
            if(!isMouseOver){
                toast.dismiss();
                setIsMouseOver(false);
            }
        }

        if(titleElement){
            titleElement.addEventListener("click", goBack);
            titleElement.addEventListener("mouseover", showNotification);
            titleElement.addEventListener("mouseleave", hideNotification);
        }

        return () => {
            if(titleElement){
                titleElement.removeEventListener("click", goBack);
                titleElement.removeEventListener("mouseover", showNotification);
                titleElement.removeEventListener("mouseleave", hideNotification);
            }
        }
    }, [year, navigate]);

    return (
        <>
            <div>
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
            </div>
            <ToastContainer/>
        </>
    )
}

export default MonthCalendar;