package boardgame;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color, int moveCount, ChessPosition chessPosition) {
        super(board, color, moveCount, chessPosition);
    }
}
