import React from 'react'
import { render, unmountComponentAtNode } from 'react-dom'
import { act } from 'react-dom/test-utils'

import TicTacToe from './tic-tac-toe'

let container = null
beforeEach(() => {
  container = document.createElement('div')
  document.body.appendChild(container)
  act(() => {
    render(<TicTacToe />, container)
  })
})

afterEach(() => {
  unmountComponentAtNode(container)
  container.remove()
  container = null
})

test('status', () => {
  const status = container.querySelector('.status')
  expect(status).not.toBeNull()
})

test('board', () => {
  const board = container.querySelector('.board')
  expect(board).not.toBeNull()

  const rows = board.querySelectorAll('.row')
  expect(rows.length).toBe(3)

  rows.forEach(row => {
    const squares = row.querySelectorAll('.square')
    expect(squares.length).toBe(3)
  })
})

test('empty square', () => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  expect(topLeft.innerHTML).toBe('')
})

test('X plays', () => {
  const status = container.querySelector('.status')
  expect(status.innerHTML).toBe('X plays')
})

test('first move', () => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')
})

test('O plays', () => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const status = container.querySelector('.status')
  expect(status.innerHTML).toBe('O plays')
})

test('second move', () => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('O')
})

test('invalid move', () => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')
})

test('horizontal win', () => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')

  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('O')

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('X')

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('O')

  const topRight = container.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()
  expect(topRight.innerHTML).toBe('X')

  const rightMid = container.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()
  expect(rightMid.innerHTML).toBe('O')

  const status = container.querySelector('.status')
  expect(status.innerHTML).toBe('O won')

  const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('')
})

test('vertical win', () => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')

  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('O')

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('X')

  const topRight = container.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()
  expect(topRight.innerHTML).toBe('O')

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('X')

  const status = container.querySelector('.status')
  expect(status.innerHTML).toBe('X won')

  const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('')
})

test('diagonal win', () => {
  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('X')

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('O')

  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')

  const rightMid = container.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()
  expect(rightMid.innerHTML).toBe('O')

  const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('X')

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('')
})

test('other diagonal win', () => {
  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('X')

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('O')

  const topRight = container.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()
  expect(topRight.innerHTML).toBe('X')

  const rightMid = container.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()
  expect(rightMid.innerHTML).toBe('O')

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('X')

  const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('')
})

test('draw', () => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')

  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('O')

  const topRight = container.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()
  expect(topRight.innerHTML).toBe('X')

  const topMid = container.querySelector('.row:nth-child(1) .square:nth-child(2)')
  topMid.click()
  expect(topMid.innerHTML).toBe('O')

  const bottomMid = container.querySelector('.row:nth-child(3) .square:nth-child(2)')
  bottomMid.click()
  expect(bottomMid.innerHTML).toBe('X')

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('O')

  const rightMid = container.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()
  expect(rightMid.innerHTML).toBe('X')

  const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('O')

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('X')

  const status = container.querySelector('.status')
  expect(status.innerHTML).toBe('Draw')
})
