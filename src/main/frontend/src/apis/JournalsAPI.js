import axios from "axios";


// 매매일지 전부 가져오는 요청
export const callJournalList = async () => {
    await axios.get("/journals/search/")
        .then(data => {
        if(!data){
            return null;
        }
        return data
        })
        .catch(function (error){
            console.error(error)
        });
}

// 백엔드 작업 필요!!
export async function callJournalListBySearch({text}){
    await axios("/journals", {
        text:text
    })
}