import JournalSearcher from "../components/journals/JournalSearcher";

const Journals = () => {

    // 테이블 컬럼
    const tableHeads = ["종목코드", "종목명", "최종거래일", "매수가격", "매수물량", "매도가격", "매도물량", "매매전략", "P&L", ""];
    const thMap = tableHeads.map(th => {
        return (
            <th>{th}</th>
        )
    });

    return (
        <>
            <div className={"journalSearcher"}>
                <JournalSearcher/>
            </div>
            <div>
                <table className={"journalsListTable"}>
                    <thead>
                    <tr>
                        {thMap}
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </>
    )
}
export default Journals;