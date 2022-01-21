package chess;

import boardgame.Board;

public class ChessMatch {

    private Board board;

    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    public ChessMatch(){
        //Creates a bord with 8 rows and 8 columns.
        board = new Board(8, 8);
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
}
