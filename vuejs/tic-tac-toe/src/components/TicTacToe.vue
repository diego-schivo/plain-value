<template>
  <div class="tic-tac-toe">
    <p class="status">{{ status }}</p>
    <div class="board" @click="handleClick">
      <div class="row">
        <button class="square">{{ board[0][0] }}</button>
        <button class="square">{{ board[0][1] }}</button>
        <button class="square">{{ board[0][2] }}</button>
      </div>
      <div class="row">
        <button class="square">{{ board[1][0] }}</button>
        <button class="square">{{ board[1][1] }}</button>
        <button class="square">{{ board[1][2] }}</button>
      </div>
      <div class="row">
        <button class="square">{{ board[2][0] }}</button>
        <button class="square">{{ board[2][1] }}</button>
        <button class="square">{{ board[2][2] }}</button>
      </div>
    </div>
  </div>
</template>

<script>
const players = ['X', 'O']

function getStatus(move, winner, draw) {
  return winner ? `${winner} won` : (draw ? 'Draw' : `${players[(move || 0) % 2]} plays`)
}
  
function getPosition(square) {
  const row = square.closest('.row')
  const board = row.closest('.board')
  return {
    row: Array.prototype.indexOf.call(board.querySelectorAll('.row'), row),
    column: Array.prototype.indexOf.call(row.querySelectorAll('.square'), square)
  }
}
  
function getWinner(board, row, column) {
  const horizontal = Array.from(Array(3), (value, index) => ({row: row, column: index}))
  const vertical = Array.from(Array(3), (value, index) => ({row: index, column: column}))
  const diagonal1 = (row === column) && Array.from(Array(3), (value, index) => ({row: index, column: index}))
  const diagonal2 = (row === (2 - column)) && Array.from(Array(3), (value, index) => ({row: index, column: 2 - index}))
  const checks = [horizontal, vertical, diagonal1, diagonal2].filter(item => item)
  const player = board[row][column]
  return checks.some(array => array.every(item => board[item.row][item.column] === player)) ? player : null
}

export default {
  name: 'TicTacToe',
  data() {
    return {
      status: getStatus(),
      board: Array.from(Array(3), () => Array(3).fill('')),
      move: 0,
      winner: null,
      draw: false
    }
  },
  methods: {
    handleClick: function(event) {
      if (this.winner || this.draw) {
        return
      }
      const { row, column } = getPosition(event.target)
      if (this.board[row][column]) {
        return
      }
      const board = this.board.map(array => array.map(item => item))
      const player = players[this.move % 2]
      board[row][column] = player
      const winner = getWinner(board, row, column)
      const draw = !winner && this.move === 8
      const move = this.move + ((!winner && !draw) ? 1 : 0)
      const status = getStatus(move, winner, draw)
  
      this.status = status
      this.board = board
      this.move = move
      this.winner = winner
      this.draw = draw
    }
  }
}
</script>

<style scoped>
.tic-tac-toe,
.tic-tac-toe .status,
.tic-tac-toe .board,
.tic-tac-toe .row {
  display: inline-flex;
  width: fit-content;
}

.tic-tac-toe {
  align-items: center;
}

.tic-tac-toe .status,
.tic-tac-toe .square {
  font: 16px sans-serif;
}

.tic-tac-toe,
.tic-tac-toe .board {
  flex-direction: column;
}

.tic-tac-toe .square {
  background-color: white;
  border: 0;
  cursor: pointer;
  display: block;
  height: 40px;
  width: 40px;
}

.tic-tac-toe .row:nth-of-type(2) {
  border-top: 1px solid black;
  border-bottom: 1px solid black;
}

.tic-tac-toe .square:nth-of-type(2) {
  border-left: 1px solid black;
  border-right: 1px solid black;
}


.tic-tac-toe .square:focus {
  outline: none;
}
</style>
