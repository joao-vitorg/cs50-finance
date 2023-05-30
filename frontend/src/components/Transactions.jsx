import { useState } from "react"
import { currencyFormat } from "../utils/currency"

export default function Transactions() {
  const [history, setHistory] = useState([
    {
      symbol: "AAPL",
      name: "Apple",
      shares: 10,
      price: 100,
      date: "2021-01-01",
    },
  ])

  return (
    <table className="table">
      <thead>
        <tr>
          <th className="text-start">Symbol</th>
          <th className="text-end">Shares</th>
          <th className="text-end">Price</th>
          <th className="text-end">Transacted</th>
        </tr>
      </thead>
      <tbody>
        {history.map((stock) => (
          <tr key={stock.symbol}>
            <td className="text-start">{stock.symbol}</td>
            <td className="text-end">{stock.shares}</td>
            <td className="text-end">{currencyFormat(stock.price)}</td>
            <td className="text-end">{stock.date}</td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}
