import { async, ComponentFixture, TestBed } from '@angular/core/testing'

import { TicTacToeComponent } from './tic-tac-toe.component'

describe('TicTacToeComponent', () => {
  let component: TicTacToeComponent
  let fixture: ComponentFixture<TicTacToeComponent>
  let compiled

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicTacToeComponent ]
    })
    .compileComponents()
  }))

  beforeEach(() => {
    fixture = TestBed.createComponent(TicTacToeComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
    compiled = fixture.nativeElement
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })

  it('should have status', () => {
    const status = compiled.querySelector('.status')
    expect(status).not.toBeNull()
  })

  it('should have board', () => {
    const board = compiled.querySelector('.board')
    expect(board).not.toBeNull()
  
    const rows = board.querySelectorAll('.row')
    expect(rows.length).toBe(3)
  
    rows.forEach(row => {
      const squares = row.querySelectorAll('.square')
      expect(squares.length).toBe(3)
    })
  })
  
  it('should have empty square', () => {
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    expect(topLeft.innerHTML).toBe('')
  })
  
  it('should display first player', () => {
    const status = compiled.querySelector('.status')
    expect(status.innerHTML).toBe('X plays')
  })
  
  it('should display first move', () => {
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    topLeft.click()
    fixture.detectChanges()
    expect(topLeft.innerHTML).toBe('X')
  })
  
  it('should display second player', () => {
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    topLeft.click()
  
    const status = compiled.querySelector('.status')
    fixture.detectChanges()
    expect(status.innerHTML).toBe('O plays')
  })
  
  it('should display second move', () => {
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    topLeft.click()
  
    const center = compiled.querySelector('.row:nth-child(2) .square:nth-child(2)')
    center.click()
    fixture.detectChanges()
    expect(center.innerHTML).toBe('O')
  })
  
  it('should prevent invalid move', () => {
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    topLeft.click()
    topLeft.click()
    fixture.detectChanges()
    expect(topLeft.innerHTML).toBe('X')
  })
  
  it('should detect horizontal win', () => {
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    topLeft.click()
  
    const center = compiled.querySelector('.row:nth-child(2) .square:nth-child(2)')
    center.click()
  
    const bottomLeft = compiled.querySelector('.row:nth-child(3) .square:nth-child(1)')
    bottomLeft.click()
  
    const leftMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(1)')
    leftMid.click()
  
    const topRight = compiled.querySelector('.row:nth-child(1) .square:nth-child(3)')
    topRight.click()
  
    const rightMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(3)')
    rightMid.click()
  
    const status = compiled.querySelector('.status')
    fixture.detectChanges()
    expect(status.innerHTML).toBe('O won')
  
    const bottomRight = compiled.querySelector('.row:nth-child(3) .square:nth-child(3)')
    bottomRight.click()
    fixture.detectChanges()
    expect(bottomRight.innerHTML).toBe('')
  })
  
  it('should detect vertical win', () => {
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    topLeft.click()
  
    const center = compiled.querySelector('.row:nth-child(2) .square:nth-child(2)')
    center.click()
  
    const leftMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(1)')
    leftMid.click()
  
    const topRight = compiled.querySelector('.row:nth-child(1) .square:nth-child(3)')
    topRight.click()
  
    const bottomLeft = compiled.querySelector('.row:nth-child(3) .square:nth-child(1)')
    bottomLeft.click()
  
    const status = compiled.querySelector('.status')
    fixture.detectChanges()
    expect(status.innerHTML).toBe('X won')
  
    const bottomRight = compiled.querySelector('.row:nth-child(3) .square:nth-child(3)')
    bottomRight.click()
    fixture.detectChanges()
    expect(bottomRight.innerHTML).toBe('')
  })
  
  it('should detect diagonal win', () => {
    const center = compiled.querySelector('.row:nth-child(2) .square:nth-child(2)')
    center.click()
  
    const leftMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(1)')
    leftMid.click()
  
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    topLeft.click()
  
    const rightMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(3)')
    rightMid.click()
  
    const bottomRight = compiled.querySelector('.row:nth-child(3) .square:nth-child(3)')
    bottomRight.click()
  
    const bottomLeft = compiled.querySelector('.row:nth-child(3) .square:nth-child(1)')
    bottomLeft.click()
    fixture.detectChanges()
    expect(bottomLeft.innerHTML).toBe('')
  })
  
  it('should detect other diagonal win', () => {
    const center = compiled.querySelector('.row:nth-child(2) .square:nth-child(2)')
    center.click()
  
    const leftMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(1)')
    leftMid.click()
  
    const topRight = compiled.querySelector('.row:nth-child(1) .square:nth-child(3)')
    topRight.click()
  
    const rightMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(3)')
    rightMid.click()
  
    const bottomLeft = compiled.querySelector('.row:nth-child(3) .square:nth-child(1)')
    bottomLeft.click()
  
    const bottomRight = compiled.querySelector('.row:nth-child(3) .square:nth-child(3)')
    bottomRight.click()
    fixture.detectChanges()
    expect(bottomRight.innerHTML).toBe('')
  })
  
  it('should detect draw', () => {
    const topLeft = compiled.querySelector('.row:nth-child(1) .square:nth-child(1)')
    topLeft.click()
  
    const center = compiled.querySelector('.row:nth-child(2) .square:nth-child(2)')
    center.click()
  
    const topRight = compiled.querySelector('.row:nth-child(1) .square:nth-child(3)')
    topRight.click()
  
    const topMid = compiled.querySelector('.row:nth-child(1) .square:nth-child(2)')
    topMid.click()
  
    const bottomMid = compiled.querySelector('.row:nth-child(3) .square:nth-child(2)')
    bottomMid.click()
  
    const leftMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(1)')
    leftMid.click()
  
    const rightMid = compiled.querySelector('.row:nth-child(2) .square:nth-child(3)')
    rightMid.click()
  
    const bottomRight = compiled.querySelector('.row:nth-child(3) .square:nth-child(3)')
    bottomRight.click()
  
    const bottomLeft = compiled.querySelector('.row:nth-child(3) .square:nth-child(1)')
    bottomLeft.click()
  
    const status = compiled.querySelector('.status')
    fixture.detectChanges()
    expect(status.innerHTML).toBe('Draw')
  })
})
