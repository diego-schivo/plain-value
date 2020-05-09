package it.plainvalue.spring.chess;

public enum PieceType {
  KING(1),
  QUEEN(1),
  ROOKS(2),
  KNIGHTS(2),
  BISHOPS(2),
  PAWN(8);

  private int count;

  private PieceType(int count) {
    this.count = count;
  }

  public int getCount() {
    return count;
  }
}
