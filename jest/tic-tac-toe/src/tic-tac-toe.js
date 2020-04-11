function ticTacToe(element) {
  const players = ['X', 'O']
  let move = 0
  let winner = null
  let draw = false

  const status = document.createElement('p')
  status.classList.add('status')
  status.innerHTML = `${players[move % 2]} plays`
  element.appendChild(status)

  const board = document.createElement('div')
  board.classList.add('board')
  element.appendChild(board)

  for (const i of Array(3).keys()) {
    const row = document.createElement('div')
    row.classList.add('row')
    board.appendChild(row)

    for (const j of Array(3).keys()) {
      const square = document.createElement('button')
      square.classList.add('square')
      row.appendChild(square)
    }
  }

  board.addEventListener('click', event => {
    if (winner || draw) {
      return
    }
    if (!event.target.classList.contains('square')) {
      return
    }
    const squareEl = event.target
    if (squareEl.innerHTML !== '') {
      return
    }
    const player = players[move % 2]
    squareEl.innerHTML = player
    const rowEl = squareEl.closest('.row')
    const rowIndex = Array.prototype.indexOf.call(board.querySelectorAll('.row'), rowEl)
    const squareIndex = Array.prototype.indexOf.call(rowEl.querySelectorAll('.square'), squareEl)
    const horizontal = Array.from(Array(3), (value, index) => [rowIndex, index])
    const vertical = Array.from(Array(3), (value, index) => [index, squareIndex])
    const diagonal1 = (rowIndex === squareIndex) && Array.from(Array(3), (value, index) => [index, index])
    const diagonal2 = (rowIndex === (2 - squareIndex)) && Array.from(Array(3), (value, index) => [index, 2 - index])
    const checks = [horizontal, vertical, diagonal1, diagonal2].filter(item => item)
    winner = checks.some(check => check.every(([rowIndex2, squareIndex2]) => {
      const squareEl2 = board.querySelector(`.row:nth-child(${rowIndex2 + 1}) .square:nth-child(${squareIndex2 + 1})`)
      return squareEl2.innerHTML === player
    })) ? player: null
    draw = !winner && Array.prototype.every.call(board.querySelectorAll('.square'), squareEl2 => squareEl2.innerHTML !== '')
    if (winner) {
      status.innerHTML = `${player} won`
    } else if (draw) {
      status.innerHTML = 'Draw'
    } else {
      move++
      status.innerHTML = `${players[move % 2]} plays`
    }
  })
}

module.exports = ticTacToe
