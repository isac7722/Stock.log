

const JournalDetail = ({journalList}) => {

    // ["종목코드", "종목명", "최종거래일", "매수가격", "매수물량", "매도가격", "매도물량", "매매전략", "P&L", "수정"]
    const journalMap = journalList.map(journal => {
        return (
            <>
                <tr>
                    <td>
                        {journal.stockCode}
                    </td>
                    <td>
                        {journal.stockName}
                    </td>
                    <td>
                        {journal.trades.tradeDate}
                    </td>
                    <td>
                        {journal.trades.price}
                    </td>
                    <td>
                        {journal.trades.quantity}
                    </td>
                    <td>
                        {journal.trades.price}
                    </td>
                    <td>
                        {journal.trades.quantity}
                    </td>
                    <td>
                        {journal.strategyId}
                    </td>
                    <td>

                    </td>
                    <td>
                        수정
                    </td>
                </tr>
            </>
        )
    })

    return(
        <>
            {journalMap}
        </>
    )
}

export default JournalDetail;