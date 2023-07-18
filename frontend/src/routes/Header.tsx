import { Link } from "react-router-dom";

export default function Header() {
  return (
    <header>
      <nav className="bg-light border navbar navbar-expand-md navbar-light">
        <div className="container-fluid">
          <Link className="navbar-brand fs-2" to="/">
            <span className="text-primary">C</span>
            <span className="text-danger">$</span>
            <span className="text-warning">5</span>
            <span className="text-success">0</span>
            <span className="text-danger">Finance</span>
          </Link>
          <button
            aria-controls="navbar"
            aria-expanded="false"
            aria-label="Toggle navigation"
            className="navbar-toggler"
            data-bs-target="#navbar"
            data-bs-toggle="collapse"
            type="button"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbar">
            {/*{% if session["user_id"] %}*/}
            <ul className="navbar-nav me-auto mt-2">
              <li className="nav-item">
                <Link className="nav-link" to="/quote">
                  Quote
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/buy">
                  Buy
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/sell">
                  Sell
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/history">
                  History
                </Link>
              </li>
            </ul>
            <ul className="navbar-nav ms-auto mt-2">
              <li className="nav-item">
                <Link className="nav-link" to="/logout">
                  Log Out
                </Link>
              </li>
            </ul>
            {/*{% else %}*/}
            {/*<ul className="navbar-nav ms-auto mt-2">*/}
            {/*  <li className="nav-item"><a className="nav-link" href="/register">Register</a></li>*/}
            {/*  <li className="nav-item"><a className="nav-link" href="/login">Log In</a></li>*/}
            {/*</ul>*/}
            {/*{% endif %}*/}
          </div>
        </div>
      </nav>
    </header>
  )
}
