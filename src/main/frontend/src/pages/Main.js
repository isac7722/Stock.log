import useJournalStore from "../dataStore/JournalStore";
import {useEffect} from "react";

const Main = () => {

    // test
    const {journalList, setJournalList} = useJournalStore();

    const openingData = async () => {
        await setJournalList();
    }

    useEffect(() => {
        openingData();
        console.log(journalList);
    }, []);

    return (
        <>
        </>
    )
}
export default Main;