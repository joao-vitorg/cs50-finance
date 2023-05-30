import { useState, useEffect } from "react"
import { Await } from "react-router-dom"
import { currencyFormat } from "../utils/currency"

export default function Portifolio() {
  const [portifolio, setPortifolio] = useState([])

  useEffect(() => {
    fetch("http://localhost:8080/user/1/stock")
      .then((response) => response.json())
      .then((data) =>
        setPortifolio(
          data.map(({ id, amount, stock: { symbol, companyName, latestPrice } }) => {
            return { id, amount, symbol, companyName, latestPrice }
          })
        )
      )
  }, [])

  return (
    <table className="table table-striped">
      <thead>
        <tr>
          <th className="text-start">Symbol</th>
          <th className="text-start">Name</th>
          <th className="text-end">Shares</th>
          <th className="text-end">Price</th>
          <th className="text-end">TOTAL</th>
        </tr>
      </thead>
      <tbody>
        {portifolio.map((stock) => (
          <tr key={stock.id}>
            <td className="text-start">{stock.symbol}</td>
            <td className="text-start">{stock.companyName}</td>
            <td className="text-end">{stock.amount}</td>
            <td className="text-end">{currencyFormat(stock.latestPrice)}</td>
            <td className="text-end">{currencyFormat(stock.amount * stock.latestPrice)}</td>
          </tr>
        ))}
      </tbody>
      <tfoot>
        <tr>
          <td className="border-0 fw-bold text-end" colSpan="4">
            Cash
          </td>
          <td className="border-0 text-end">cash</td>
        </tr>
        <tr>
          <td className="border-0 fw-bold text-end" colSpan="4">
            TOTAL
          </td>
          <td className="border-0 w-bold text-end">total</td>
        </tr>
      </tfoot>
    </table>
  )
}
