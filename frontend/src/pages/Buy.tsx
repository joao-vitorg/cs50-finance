import { Input } from "@/ui/input.tsx";
import { Button } from "@/ui/button.tsx";

export default function Buy() {
	return (
		<div className={"container"}>
			<form action="/buy" method="post" className={"flex flex-col gap-3"}>
				<Input required name="symbol" placeholder="Symbol" type="text" autoFocus />
				<Input required name="shares" placeholder="Shares" type="number" min="1" />
				<Button type="submit">Buy</Button>
			</form>
		</div>
	);
}
