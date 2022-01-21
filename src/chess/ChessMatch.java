package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    public ChessMatch(){
        //Creates a bord with 8 rows and 8 columns and calls the initial setup.
        board = new Board(8, 8);
        initialSetup();
    }

    //Return a Matrix
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

        for(int i=0; i<board.getRows(); i++){
            for(int j=0; j< board.getColumns(); j++){
                //For each Piece in the board I'm going to do a downcast to ChessPiece
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }

    private void initialSetup(){
        //Place the pieces on the board
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
        board.placePiece(new King(board, Color.BLACK), new Position(0 , 4));
        board.placePiece(new King(board, Color.WHITE), new Position(7 , 4));

    }
}
