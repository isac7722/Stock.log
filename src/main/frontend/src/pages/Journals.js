import JournalSearcher from "../components/journals/JournalSearcher";
import Table from "react-bootstrap/Table";
import "../css/journals/journals.css"
import {useState} from "react";
import JournalDetail from "../components/journals/journalDetail";

const Journals = () => {

    const [journalList, setJournalList] = useState([{}]);

    // 테이블 컬럼
    const tableHeads = ["종목코드", "종목명", "최종거래일", "매수가격", "매수물량", "매도가격", "매도물량", "매매전략", "P&L", "수정"];
    const thMap = tableHeads.map(th => {
        return (
            <th>{th}</th>
        )
    });

    return (
        <>
            <div className={"journalSearcher"}>
                <JournalSearcher journalList={journalList} setJournalList={setJournalList}/>
            </div>
            <div>
                <Table bordered hover>
                    <thead>
                    <tr>
                        {thMap}
                    </tr>
                    </thead>
                    <tbody>
                        <JournalDetail journalList={journalList}/>
                    </tbody>
                </Table>
            </div>
        </>
    )
}
export default Journals;