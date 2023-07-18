import { useEffect, useState } from "react";
import { currencyFormat } from "../utils/currency";
import { Api, ClientStockVo } from "../utils/Api.ts";

export default function Portifolio() {
	const [portifolio, setPortifolio] = useState(Array<ClientStockVo>);

	useEffect(() => {
		const api = new Api();

		api.clientStock.findClientStockByClientId(1)
			// eslint-disable-next-line @typescript-eslint/no-unsafe-argument
			.then(({ data: { content } }) => setPortifolio(content ?? []))
			.catch((error) => console.log(error));
	}, []);

	return (
		<table className="table table-striped">
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
				<td className="border-0 fw-bold text-end">
					Cash
				</td>
				<td className="border-0 text-end">cash</td>
			</tr>
			<tr>
				<td className="border-0 fw-bold text-end">
					TOTAL
				</td>
				<td className="border-0 w-bold text-end">total</td>
			</tr>
			</tfoot>
		</table>
	);
}
