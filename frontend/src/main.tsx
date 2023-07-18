import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Buy from "./routes/Buy.tsx";
import Portifolio from "./routes/Portifolio.tsx";
import App from "./App.tsx";

const router = createBrowserRouter([
	{
		path: "/", element: <App />,
		children: [
			{ path: "portifolio", element: <Portifolio /> },
			{ path: "buy", element: <Buy /> }
		]
	}
]);


ReactDOM.createRoot(document.getElementById("root")!).render(
	<React.StrictMode>
		<RouterProvider router={router} />
	</React.StrictMode>
);
