import axios from "axios";


export const callJournalList = async () => {
    await axios.get("/journals/search/");
}

export async function callJournalListBySearch({text}){
    await axios.get("/journals/search/");
};