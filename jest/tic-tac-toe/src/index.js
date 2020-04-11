const ticTacToe = require('./tic-tac-toe.js');

document.querySelectorAll('.tic-tac-toe').forEach(element => {
  ticTacToe(element)
})