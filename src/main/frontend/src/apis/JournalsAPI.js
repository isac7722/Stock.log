import axios from "axios";


const journalList = async () => {
    await axios.get("/journals/search/");
}