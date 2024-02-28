import axios from "axios";


// 매매일지 전부 가져오는 요청
export const callJournalList = async () => {
    return await axios.get("http://localhost:8080/journals/search",
        {withCredentials: true});
}

// 백엔드 작업 필요!!
export async function callJournalListBySearch({text}){
    await axios("http://localhost:8080/journals", {
        text:text
    })
}