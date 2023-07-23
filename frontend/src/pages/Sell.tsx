export default function Sell() {
	return (
		<form action="/sell" method="post">
			<div className="mb-3">
				<select required className="form-select mx-auto w-auto" name="symbol">
					<option disabled selected>
						Symbol
					</option>
					{/*{% for symbol in symbols %}*/}
					{/*<option value="{{ symbol }}">{{ symbol }}</option>*/}
					{/*{% endfor %}*/}
				</select>
			</div>
			<div className="mb-3">
				<input
					autoComplete="off"
					autoFocus
					className="form-control mx-auto w-auto"
					id="shares"
					name="shares"
					placeholder="Shares"
					type="number"
					min="1"
					required
				/>
			</div>
			<button className="btn btn-primary" type="submit">
				Sell
			</button>
		</form>
	);
}
