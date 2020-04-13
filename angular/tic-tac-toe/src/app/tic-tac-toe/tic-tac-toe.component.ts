import { Component, OnInit } from '@angular/core';

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

@Component({
  selector: 'app-tic-tac-toe',
  templateUrl: './tic-tac-toe.component.html',
  styleUrls: [ './tic-tac-toe.component.css' ]
})
export class TicTacToeComponent implements OnInit {
  status = getStatus(0, null, false);
  board = Array.from(Array(3), () => Array(3).fill(''));
  move = 0;
  winner = null;
  draw = false;

  constructor() {
  }

  ngOnInit() {
  }

  mark(event) {
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
	
    this.status = getStatus(move, winner, draw)
    this.board = board
    this.move = move
    this.winner = winner
    this.draw = draw
  }
}