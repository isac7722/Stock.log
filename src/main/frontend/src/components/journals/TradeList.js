import Table from "react-bootstrap/Table";


const TradeList = ({trades}) => {

    // Accordion.Body 영역

    const tradesMaping = trades.map(trade => (
                <tr>
                    <td>
                        {trade.tradeDate}
                    </td>
                    <td>
                        {trade.status}
                    </td>
                    <td>
                        {trade.price}
                    </td>
                    <td>
                        {trade.quantity}
                    </td>
                    <td>
                        P&L
                    </td>
                </tr>
    ));

    return (
        <>
            <Table>
                <thead>
                <tr>
                    <td>
                        매매날짜
                    </td>
                    <td>
                        매수/매도
                    </td>
                    <td>
                        매매가
                    </td>
                    <td>
                        매매량
                    </td>
                    <td>
                        P&L
                    </td>
                </tr>
                </thead>
                <tbody>
                {tradesMaping}
                </tbody>
            </Table>
        </>
    )
}

export default TradeList;