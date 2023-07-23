import { useEffect, useState } from "react";
import { Api, ClientStockVo } from "../utils/Api.ts";
import { currencyFormat } from "../utils/currency.ts";

export default function Portifolio() {
	const [portifolio, setPortifolio] = useState(Array<ClientStockVo>);

	useEffect(() => {
		const api = new Api();

		api.clientStock
			.findClientStockByClientId(1)
			.then(({ data: { content } }) => setPortifolio(content ?? []))
			.catch((error) => console.log(error));
	}, []);

	return (
		<table className="table table-auto">
			<thead>
				<tr>
					<th className="text-start">Symbol</th>
					<th className="text-start">Name</th>
					<th className="text-start">Price</th>
					<th className="text-end">Shares</th>
					<th className="text-end">TOTAL</th>
				</tr>
			</thead>
			<tbody>
				{portifolio?.map((stock) => (
					<tr key={stock.id}>
						<td className="text-start">{stock.stock.symbol}</td>
						<td className="text-start">{stock.stock.name}</td>
						<td className="text-start">{stock.stock.price}</td>
						<td className="text-end">{stock.shares}</td>
						<td className="text-end">{currencyFormat(stock.total)}</td>
					</tr>
				))}
			</tbody>
			<tfoot>
				<tr>
					<td className="fw-bold border-0 text-end">Cash</td>
					<td className="border-0 text-end">cash</td>
				</tr>
				<tr>
					<td className="fw-bold border-0 text-end">TOTAL</td>
					<td className="w-bold border-0 text-end">total</td>
				</tr>
			</tfoot>
		</table>
	);
}
