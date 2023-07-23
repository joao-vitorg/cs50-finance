export default function Login() {
	return (
		<form action="/login" method="post">
			<div className="mb-3">
				<input
					autoComplete="off"
					autoFocus
					className="form-control mx-auto w-auto"
					id="username"
					name="username"
					placeholder="Username"
					type="text"
					required
				/>
			</div>
			<div className="mb-3">
				<input
					className="form-control mx-auto w-auto"
					id="password"
					name="password"
					placeholder="Password"
					type="password"
					required
				/>
			</div>
			<button className="btn btn-primary" type="submit">
				Log In
			</button>
		</form>
	);
}
