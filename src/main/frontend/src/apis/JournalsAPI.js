import axios from "axios";
import data from "bootstrap/js/src/dom/data";

const accessToken = localStorage.getItem("accessToken");

const journalAxios = axios.create({
    // 기본 URL 설정
    baseURL: "http://localhost:8080",
    // 기본 헤더 설정
    headers: {
        Authorization: `BEARER ${accessToken}`,
    },
    responseType:"json"
});

export const callJournalList = async () => {

    try {
        const response = await journalAxios.get("/journals/search", {
            withCredentials: true
        });

        return response.data;

    }catch (error){
        console.error(error);
    }
};

// 백엔드 작업 필요!!
export async function callJournalListBySearch({text}){
    await axios("http://localhost:8080/journals", {
        text:text
    })
}