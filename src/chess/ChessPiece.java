package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount;
//
//    private ChessPosition chessPosition;

    public ChessPiece(Board board) {
        super(board);
    }

    public ChessPiece(Board board, Color color){
        super(board);
        this.color = color;

    }

//    public ChessPiece(Board board, Color color, int moveCount, ChessPosition chessPosition) {
//        super(board);
//        this.color = color;
//        this.moveCount = moveCount;
//        this.chessPosition = chessPosition;
//    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

//    public ChessPosition getChessPosition(){
//        return chessPosition;
//    }
    public boolean isThereOpponentPiece(Position position){
        return position != null;
    }
    public void increaseMoveCount(int amount){
        moveCount += amount;
    }
    public void decreaseMoveCount(int amount){
        moveCount += amount;
    }
}
