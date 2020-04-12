import Vue from 'vue'
import { enableAutoDestroy, mount } from '@vue/test-utils'
import TicTacToe from '@/components/TicTacToe.vue'

let container
beforeEach(() => {
  const wrapper = mount(TicTacToe)
  container = wrapper.element
})

enableAutoDestroy(afterEach)

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

test('first move', done => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  Vue.nextTick(() => {
    expect(topLeft.innerHTML).toBe('X')
    done()
  })
})

test('O plays', done => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const status = container.querySelector('.status')
  Vue.nextTick(() => {
    expect(status.innerHTML).toBe('O plays')
    done()
  })
})

test('second move', done => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()
  Vue.nextTick(() => {
    expect(center.innerHTML).toBe('O')
    done()
  })
})

test('invalid move', done => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()
  topLeft.click()
  Vue.nextTick(() => {
    expect(topLeft.innerHTML).toBe('X')
    done()
  })
})

test('horizontal win', done => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()

  const topRight = container.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()

  const rightMid = container.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()

  const status = container.querySelector('.status')
  Vue.nextTick(() => {
    expect(status.innerHTML).toBe('O won')

    const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
    bottomRight.click()
    Vue.nextTick(() => {
      expect(bottomRight.innerHTML).toBe('')
      done()
    })
  })
})

test('vertical win', done => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()

  const topRight = container.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()

  const status = container.querySelector('.status')
  Vue.nextTick(() => {
    expect(status.innerHTML).toBe('X won')

    const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
    bottomRight.click()
    Vue.nextTick(() => {
      expect(bottomRight.innerHTML).toBe('')
      done()
    })
  })
})

test('diagonal win', done => {
  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()

  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const rightMid = container.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()

  const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()
  Vue.nextTick(() => {
    expect(bottomLeft.innerHTML).toBe('')
    done()
  })
})

test('other diagonal win', done => {
  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()

  const topRight = container.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()

  const rightMid = container.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()

  const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()
  Vue.nextTick(() => {
    expect(bottomRight.innerHTML).toBe('')
    done()
  })
})

test('draw', done => {
  const topLeft = container.querySelector('.row:nth-child(1) .square:nth-child(1)')
  topLeft.click()

  const center = container.querySelector('.row:nth-child(2) .square:nth-child(2)')
  center.click()

  const topRight = container.querySelector('.row:nth-child(1) .square:nth-child(3)')
  topRight.click()

  const topMid = container.querySelector('.row:nth-child(1) .square:nth-child(2)')
  topMid.click()

  const bottomMid = container.querySelector('.row:nth-child(3) .square:nth-child(2)')
  bottomMid.click()

  const leftMid = container.querySelector('.row:nth-child(2) .square:nth-child(1)')
  leftMid.click()

  const rightMid = container.querySelector('.row:nth-child(2) .square:nth-child(3)')
  rightMid.click()

  const bottomRight = container.querySelector('.row:nth-child(3) .square:nth-child(3)')
  bottomRight.click()

  const bottomLeft = container.querySelector('.row:nth-child(3) .square:nth-child(1)')
  bottomLeft.click()

  const status = container.querySelector('.status')
  Vue.nextTick(() => {
    expect(status.innerHTML).toBe('Draw')
    done()
  })
})
