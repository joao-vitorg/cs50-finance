export default function Buy() {
  return (
    <form action="/buy" method="post">
      <div className="mb-3">
        <input autoComplete="off" autoFocus className="form-control mx-auto w-auto" id="symbol" name="symbol" placeholder="Symbol" type="text" required />
      </div>
      <div className="mb-3">
        <input autoComplete="off" autoFocus className="form-control mx-auto w-auto" name="shares" placeholder="Shares" type="number" min="1" required />
      </div>
      <button className="btn btn-primary" type="submit">
        Buy
      </button>
    </form>
  )
}
