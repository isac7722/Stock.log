import Accordion from "react-bootstrap/Accordion";
import TradeList from "./TradeList";
import NoteList from "./NoteList";
import useJournalStore from "../../dataStore/JournalStore";


const JournalList = () => {

    const {journalList} = useJournalStore();
    // ["종목코드", "종목명", "최종거래일", "매수가격", "매수물량", "매도가격", "매도물량", "매매전략", "P&L", "수정"]
    // 아코디언 헤드 영역

    const journalMap = journalList.map(journal => (
            <>
                <tr>
                    <Accordion defaultActiveKey={['0']} alwaysOpen>
                        <Accordion.Item eventKey={journal.stockCode}>
                            <Accordion.Header>
                                <td>
                                    {journal.stockCode}
                                </td>
                                <td>
                                    {journal.stockName}
                                </td>
                                <td>
                                    {journal.lastTradedDate}
                                </td>
                                <td>
                                    {journal.buyPrice}
                                </td>
                                <td>
                                    {journal.buyQty}
                                </td>
                                <td>
                                    {journal.sellPrice}
                                </td>
                                <td>
                                    {journal.sellQty}
                                </td>
                                <td>
                                    {journal.strategy}
                                </td>
                                <td>
                                    {journal.profit}
                                </td>
                                <td>
                                    수정버튼
                                </td>
                            </Accordion.Header>
                            <Accordion.Body>
                                <TradeList/>
                                <h3>NOTES</h3>
                                <NoteList/>
                            </Accordion.Body>
                        </Accordion.Item>
                    </Accordion>

                </tr>
            </>
    ))

    return (
        <>
            {journalMap}
        </>
    )
}

export default JournalList;