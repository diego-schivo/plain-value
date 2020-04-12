import React from 'react'
import './tic-tac-toe.css'

export default class TicTacToe extends React.Component {

  constructor(props) {
    super(props)
    this.players = ['X', 'O']
    this.state = {
      status: this.getStatus(),
      board: Array.from(Array(3), () => Array(3).fill('')),
      move: 0,
      winner: null,
      draw: false
    }
  }

  render() {
    return (
      <div className="tic-tac-toe">
        <p className="status">{this.state.status}</p>
        <div className="board" onClick={this.handleClick}>
          <div className="row">
            <button className="square">{this.state.board[0][0]}</button>
            <button className="square">{this.state.board[0][1]}</button>
            <button className="square">{this.state.board[0][2]}</button>
          </div>
          <div className="row">
            <button className="square">{this.state.board[1][0]}</button>
            <button className="square">{this.state.board[1][1]}</button>
            <button className="square">{this.state.board[1][2]}</button>
          </div>
          <div className="row">
            <button className="square">{this.state.board[2][0]}</button>
            <button className="square">{this.state.board[2][1]}</button>
            <button className="square">{this.state.board[2][2]}</button>
          </div>
        </div>
      </div>
    )
  }

  handleClick = event => {
    if (this.state.winner || this.state.draw) {
      return
    }
    const { row, column } = this.getPosition(event.target)
    if (this.state.board[row][column]) {
      return
    }
    this.setState(prevState => {
      const board = prevState.board.map(array => array.map(item => item))
      const player = this.players[prevState.move % 2]
      board[row][column] = player
      const winner = this.getWinner(board, row, column)
      const draw = !winner && prevState.move === 8
      const move = prevState.move + ((!winner && !draw) ? 1 : 0)
      const status = this.getStatus(move, winner, draw)

      return {
        status: status,
        board: board,
        move: move,
        winner: winner,
        draw: draw
      }
    })
  }

  getStatus(move, winner, draw) {
    return winner ? `${winner} won` : (draw ? 'Draw' : `${this.players[(move || 0) % 2]} plays`)
  }

  getPosition(square) {
    const row = square.closest('.row')
    const board = row.closest('.board')
    return {
      row: Array.prototype.indexOf.call(board.querySelectorAll('.row'), row),
      column: Array.prototype.indexOf.call(row.querySelectorAll('.square'), square)
    }
  }

  getWinner(board, row, column) {
    const horizontal = Array.from(Array(3), (value, index) => ({row: row, column: index}))
    const vertical = Array.from(Array(3), (value, index) => ({row: index, column: column}))
    const diagonal1 = (row === column) && Array.from(Array(3), (value, index) => ({row: index, column: index}))
    const diagonal2 = (row === (2 - column)) && Array.from(Array(3), (value, index) => ({row: index, column: 2 - index}))
    const checks = [horizontal, vertical, diagonal1, diagonal2].filter(item => item)
    const player = board[row][column]
    return checks.some(array => array.every(item => board[item.row][item.column] === player)) ? player : null
  }
}
