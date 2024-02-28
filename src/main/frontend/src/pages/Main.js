import useJournalStore from "../dataStore/JournalStore";
import {useEffect} from "react";

const Main = () => {

    // useStore처럼 구조 분해 할당으로 사용한다.
    const {journalList, setJournalList} = useJournalStore();

    async function openingData(){
        await setJournalList();
    }

    // setJournalList를 동작할 때마다 리랜더링한다. 만약 openingData를 지정하면 무한히 반복요청을 보낸다.
    useEffect(() => {
        openingData();
    }, [setJournalList]);
    console.log(journalList);

    return (
        <>
        </>
    )
}
export default Main;