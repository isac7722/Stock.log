import useJournalStore from "../dataStore/JournalStore";

const Main = () => {

    // test
    const {journalList, setJournalList} = useJournalStore();

    const test = async () => {
        console.log(journalList);
        await setJournalList();
    }

    return (
        <>
            <button onClick={test}>콘솔로 확인</button>
        </>
    )
}
export default Main;