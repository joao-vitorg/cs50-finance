import { Link, Outlet } from "react-router-dom";

export default function App() {
	return (
		<>
			<header>
				<h1>React Router Contacts</h1>
				<nav>
					<ul>
						<li>
							<Link to={`/portifolio`}>Portifolio</Link>
						</li>
						<li>
							<Link to={`/buy`}>Buy</Link>
						</li>
					</ul>
				</nav>
			</header>
			<main>
				<Outlet />
			</main>
		</>
	);
}
