const ticTacToe = require('../src/tic-tac-toe')

let element

beforeEach(() => {
  document.body.innerHTML = '<div class="tic-tac-toe"></div>'
  element = document.querySelector('.tic-tac-toe')
  ticTacToe(element)
})

test('status', () => {
  const status = element.querySelector('.status')
  expect(status).not.toBeNull()
})

test('board', () => {
  const board = element.querySelector('.board')
  expect(board).not.toBeNull()

  const rows = board.querySelectorAll('.row')
  expect(rows.length).toBe(3)

  rows.forEach(row => {
    const squares = row.querySelectorAll('.square')
    expect(squares.length).toBe(3)
  })
})

test('empty square', () => {
  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  expect(topLeft.innerHTML).toBe('')
})

test('X plays', () => {
  const status = element.querySelector('.status')
  expect(status.innerHTML).toBe('X plays')
})

test('first move', () => {
  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')
})

test('O plays', () => {
  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const status = element.querySelector('.status')
  expect(status.innerHTML).toBe('O plays')
})

test('second move', () => {
  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const center = element.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('O')
})

test('invalid move', () => {
  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')
})

test('vertical win', () => {
  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')

  const center = element.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('O')

  const leftMid = element.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('X')

  const topRight = element.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()
  expect(topRight.innerHTML).toBe('O')

  const bottomLeft = element.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('X')

  const status = element.querySelector('.status')
  expect(status.innerHTML).toBe('X won')

  const bottomRight = element.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('')
})

test('horizontal win', () => {
  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')

  const center = element.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('O')

  const bottomLeft = element.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('X')

  const leftMid = element.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('O')

  const topRight = element.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()
  expect(topRight.innerHTML).toBe('X')

  const rightMid = element.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()
  expect(rightMid.innerHTML).toBe('O')

  const status = element.querySelector('.status')
  expect(status.innerHTML).toBe('O won')

  const bottomRight = element.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('')
})

test('diagonal win', () => {
  const center = element.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('X')

  const leftMid = element.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('O')

  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')

  const rightMid = element.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()
  expect(rightMid.innerHTML).toBe('O')

  const bottomRight = element.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('X')

  const bottomLeft = element.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('')
})

test('other diagonal win', () => {
  const center = element.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('X')

  const leftMid = element.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('O')

  const topRight = element.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()
  expect(topRight.innerHTML).toBe('X')

  const rightMid = element.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()
  expect(rightMid.innerHTML).toBe('O')

  const bottomLeft = element.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('X')

  const bottomRight = element.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('')
})

test('draw', () => {
  const topLeft = element.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  expect(topLeft.innerHTML).toBe('X')

  const center = element.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  expect(center.innerHTML).toBe('O')

  const topRight = element.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()
  expect(topRight.innerHTML).toBe('X')

  const topMid = element.querySelector('.row:nth-child(1) .square:nth-child(2)')
  topMid.click()
  expect(topMid.innerHTML).toBe('O')

  const bottomMid = element.querySelector('.row:nth-child(3) .square:nth-child(2)')
  bottomMid.click()
  expect(bottomMid.innerHTML).toBe('X')

  const leftMid = element.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()
  expect(leftMid.innerHTML).toBe('O')

  const rightMid = element.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()
  expect(rightMid.innerHTML).toBe('X')

  const bottomRight = element.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  expect(bottomRight.innerHTML).toBe('O')

  const bottomLeft = element.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  expect(bottomLeft.innerHTML).toBe('X')

  const status = element.querySelector('.status')
  expect(status.innerHTML).toBe('Draw')
})
