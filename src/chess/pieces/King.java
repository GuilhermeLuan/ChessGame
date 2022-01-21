package boardgame;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color, int moveCount, ChessPosition chessPosition) {
        super(board, color, moveCount, chessPosition);
    }
}
