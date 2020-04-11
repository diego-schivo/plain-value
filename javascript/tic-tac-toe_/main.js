const players = ['X', 'O']
const history = [{ squares: Array(9).fill(null) }]
let gameOver = false

const moves = document.querySelector('.moves')

document.querySelectorAll('.square').forEach((element, index) => {
  element.addEventListener('click', event => {
    if (gameOver) {
      return
    }
    const squares = history[history.length - 1].squares.slice()
    if (squares[index]) {
      return
    }
    squares[index] = player()
    history.push({ squares: squares })
    element.innerHTML = squares[index]
    const winner = calculateWinner()
    if (winner) {
      document.querySelector('.status').innerHTML = `Winner: ${winner}`
      gameOver = true
      return
    }
    gameOver = squares.every(item => item)
    if (gameOver) {
      document.querySelector('.status').innerHTML = 'Game over'
      return
    }
    document.querySelector('.status').innerHTML = `Next player: ${player()}`
    const move = moves.querySelector('.move').cloneNode(true)
    const goTo = move.querySelector('.go-to')
    goTo.dataset.move = history.length - 1
    goTo.innerHTML = `Go to move #${history.length - 1}`
    moves.appendChild(move)
  })
})

moves.addEventListener('click', event => {
  if (!event.target.classList.contains('go-to')) {
    return
  }
  const step = event.target.dataset.move
  history.splice(step + 1)
  const squares = history[step].squares
  document.querySelectorAll('.square').forEach((element, index) => {
    element.innerHTML = squares[index]
  })
  Array.prototype.filter.call(moves.querySelectorAll('.move'), (element, index) => index > step).forEach(element => element.remove())
  document.querySelector('.status').innerHTML = `Next player: ${player()}`
})

const lines = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
  [0, 3, 6],
  [1, 4, 7],
  [2, 5, 8],
  [0, 4, 8],
  [2, 4, 6]
]

function calculateWinner() {
  const squares = history[history.length - 1].squares
  const line = lines.find(item => {
    const item0 = item[0]
    return item.every(item2 => item2 === item0)
  })
  return line && line[0]
}

function player() {
  return (history.length % 2) ? 'X' : 'O'
}
